package com.hqy.concurrencypackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest04 {

    private static Random random = new Random(System.currentTimeMillis());


    static class Event {

        int id;

        public Event(int id) {
            this.id = id;
        }
    }

    interface Watcher {
        //void starWartch();
        void done(Table table);
    }

    static class TaskBach implements Watcher {

        private CountDownLatch countDownLatch;

        public TaskBach(int size) {
            this.countDownLatch = new CountDownLatch(size);
        }

        @Override
        public void done(Table table) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0) {
                System.out.println("The table " + table.tableName + " finished work,[" + table + "]");
            }
        }

/*        @Override
        public void starWartch(Table table) {
            if (countDownLatch.getCount() == 0) {
                System.out.println("The table  " + table.tableName + " finished work,[" + table + "}");
            }*/
    }


    static class Table {
        String tableName;
        long sourceRecordCount = 10;
        long targetCount;
        String sourceColumSchema = "<table name='a'><colum name  = 'col1' type = 'varchar2'/></table>";
        String targetColumSchema = "";

        public Table(String tableName, long sourceRecordCount) {
            this.tableName = tableName;
            this.sourceRecordCount = sourceRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableName='" + tableName + '\'' +
                    ", sourceRecordCount=" + sourceRecordCount +
                    ", targetCount=" + targetCount +
                    ", sourceColumSchema='" + sourceColumSchema + '\'' +
                    ", targetColumSchema='" + targetColumSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(CountDownLatchTest04.Event event) {
        List<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table("table-" + event.id + "-" + i, i * 1000));
        }
        return list;
    }

    public static void main(String[] args) {

        Event[] events = {new Event(1), new Event(2)};

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (CountDownLatchTest04.Event event : events) {
            List<Table> tables = capture(event);
            TaskBach taskBach = new TaskBach(2);
            for (Table table : tables) {
                TrustSourceColums trustSourceColums = new TrustSourceColums(table, taskBach);
                TrustSourceRecordCount trustSourceRecordCount = new TrustSourceRecordCount(table, taskBach);
                executorService.submit(trustSourceColums);
                executorService.submit(trustSourceRecordCount);
            }
        }

    }

    private static class TrustSourceRecordCount implements Runnable {

        private final Table table;

        private final TaskBach taskBach;

        private TrustSourceRecordCount(Table table, TaskBach taskBach) {
            this.table = table;
            this.taskBach = taskBach;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetCount = table.sourceRecordCount;
            //System.out.println("The table " + table.tableName + " target record count captrue done and update the ");
            taskBach.done(table);
        }
    }


    private static class TrustSourceColums implements Runnable {

        private final Table table;

        private final TaskBach taskBach;

        private TrustSourceColums(Table table, TaskBach taskBach) {
            this.table = table;
            this.taskBach = taskBach;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.targetColumSchema = table.sourceColumSchema;
            //System.out.println("The table " + table.tableName + " target columns captrue done and update the ");
            taskBach.done(table);
        }
    }

}
