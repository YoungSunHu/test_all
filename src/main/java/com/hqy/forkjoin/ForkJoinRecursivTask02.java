package com.hqy.forkjoin;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ForkJoinRecursivTask02 {

    private final static int MAX_THRESHOLD = 3;

    private final static AtomicInteger SUM = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        final ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.submit(() -> {
            System.out.println("====================");
        }, new CalculateRecursiveAction(0, 10));

        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);

        Optional.of(SUM).ifPresent(System.out::println);
    }


    private static class CalculateRecursiveAction extends RecursiveAction {

        private final int start;

        private final int end;

        private CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end - start) <= MAX_THRESHOLD) {
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            } else {
                int middle = (start + end) / 2;
                CalculateRecursiveAction leftActionn = new CalculateRecursiveAction(start, middle);
                CalculateRecursiveAction rightAction = new CalculateRecursiveAction(middle + 1, end);
                leftActionn.fork();
                rightAction.fork();
            }
        }
    }

}
