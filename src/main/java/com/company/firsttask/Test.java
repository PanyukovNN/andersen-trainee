package com.company.firsttask;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
        final Set<Double> res = new HashSet<>();
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
        AtomicBoolean hasError = new AtomicBoolean(false);
        AtomicInteger value = new AtomicInteger(0);
        for (int i = 0; i < TestConsts.MAX_THREADS; i++) {
            Thread thread = new Thread(() -> {
                try {
                    while (value.get() < TestConsts.N && !hasError.get()) {
                        int currentValue = value.getAndIncrement();
                        Set<Double> resultSet = TestCalc.calculate(currentValue);
                        synchronized (res) {
                            res.addAll(resultSet);
                        }
                    }
                } catch (TestException e) {
                    hasError.set(true);
                    throw new CalculationException(e.getMessage(), e);
                }
            });
            thread.setUncaughtExceptionHandler(handler);
            threads.add(thread);
        }
        return threads;
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
