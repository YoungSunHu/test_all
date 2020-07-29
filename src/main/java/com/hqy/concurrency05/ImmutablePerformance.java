package com.hqy.concurrency05;

/**
 * 不可变对象性能测试
 */
public class ImmutablePerformance {

    public static void main(String[] args) {
        long startTimeStamp = System.currentTimeMillis();
        SynObj synObj = new SynObj();
        synObj.setName("Young");
        for (long l = 0L; l < 1000000; l++) {
            System.out.println(synObj.toString());
        }
        long endTimeStamp = System.currentTimeMillis();
        System.out.println("Elasped time :" + (endTimeStamp - startTimeStamp));

    }

}

class ImmutableObj {
    private final String name;

    ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}


class SynObj {
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "synObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
