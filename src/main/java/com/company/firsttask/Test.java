package com.company.firsttask;

import org.apache.log4j.Logger;

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

    private static final Logger LOG = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        final Set<Double> res = new CopyOnWriteArraySet<>();
        List<Thread> threads = createThreads(res);
        threads.forEach(Thread::start);
        waitAllThreads(threads);
        long endTime = System.currentTimeMillis();
        LOG.info(res);
        LOG.info(endTime - startTime);
    }

    private static List<Thread> createThreads(Set<Double> res) {
        List<Thread> threads = new ArrayList<>();
        Thread.UncaughtExceptionHandler handler = (th, ex) -> LOG.error("Uncaught exception: " + ex);
        for (int i = 0; i < TestConsts.MAX_THREADS; i++) {
            List<Integer> valuesForThread = findValues(i);
            Thread thread = new Thread(() -> {
                try {
                    for (Integer j : valuesForThread) {
                        res.addAll(TestCalc.calculate(j));
                    }
                } catch (TestException e) {
                    threads.forEach(Thread::interrupt);
                    throw new CalculationException(e.getMessage(), e);
                }
            });
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
            throw new CalculationException(e.getMessage(), e);
        }
    }
}
