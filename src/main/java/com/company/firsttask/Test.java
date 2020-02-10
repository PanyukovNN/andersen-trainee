package com.company.firsttask;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Необходимо уменьшить время выполнения вычислений.
 * <p>
 * Можно изменять этот класс или добавить новый. Решение должно быть максимально простым, без использования экзекуторов.
 * (Runnable, Threads). Аккуратно обработайте потенциальные ошибки (разумеется вычисления если появились ошибки нужно
 * остановить). Количество потоков должно быть ограничено значением константы com.com.company.firsttask.TestConsts#MAX_THREADS.
 */
public class Test {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        final Set<Double> res = new CopyOnWriteArraySet<>();
        List<Thread> threads = createThreads(res);
        waitAllThreads(threads);
        long endTime = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(endTime - startTime);
    }

    private static List<Thread> createThreads(Set<Double> res) {
        Thread.UncaughtExceptionHandler handler = (th, ex) -> System.out.println("Uncaught exception: " + ex);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < TestConsts.MAX_THREADS; i++) {
            List<Integer> valuesForThread = findValues(i);
            Runnable runnable = () -> {
                try {
                    for (Integer j : valuesForThread) {
                        if (Thread.interrupted()) {
                            throw new CalculationException("Interrupted");
                        }
                        res.addAll(TestCalc.calculate(j));
                    }
                } catch (TestException e) {
                    threads.forEach(Thread::interrupt);
                    throw new CalculationException("Error while calculation.");
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            thread.setUncaughtExceptionHandler(handler);
            threads.add(thread);
        }
        return threads;
    }

    private static List<Integer> findValues(int i) {
        final int sizeOfSlice = (int) Math.round(TestConsts.N / (double) TestConsts.MAX_THREADS);
        final int startValue = i * (sizeOfSlice);
        final int endValue = i != TestConsts.MAX_THREADS - 1
                ? Math.min(startValue + sizeOfSlice - 1, TestConsts.N - 1)
                : TestConsts.N - 1;
        return IntStream.rangeClosed(startValue, endValue).boxed().collect(Collectors.toList());
    }

    private static void waitAllThreads(List<Thread> allThreadsList) {
        try {
            for (Thread thread : allThreadsList) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println("hmm");
        }
    }
}
