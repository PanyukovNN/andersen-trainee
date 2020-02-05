package com.company.firsttask;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Необходимо уменьшить время выполнения вычислений.
 * <p>
 * Можно изменять этот класс или добавить новый. Решение должно быть максимально простым, без использования экзекуторов.
 * (Runnable, Threads). Аккуратно обработайте потенциальные ошибки (разумеется вычисления если появились ошибки нужно
 * остановить). Количество потоков должно быть ограничено значением константы com.com.company.firsttask.TestConsts#MAX_THREADS.
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Set<Double> res = new CopyOnWriteArraySet<>();
        List<Thread> threadsList = new CopyOnWriteArrayList<>();
        ThreadManager threadManager = new ThreadManager();
        runCalculation(res, threadsList, threadManager);
        waitAllThreads(threadsList);
        long endTime = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(endTime - startTime);
    }

    private static void runCalculation(Set<Double> res, List<Thread> allThreadsList, ThreadManager threadManager) throws InterruptedException {
        Thread.UncaughtExceptionHandler handler = (th, ex) ->  {
            System.out.println("Uncaught exception: " + ex);
            System.exit(1);
        };
        for (int i = 0; i < TestConsts.N; i++) {
            Thread thread = new SingleThread(i, res);
            allThreadsList.add(thread);
            thread.setUncaughtExceptionHandler(handler);
            thread.start();
            threadManager.addThreadToQueue(thread);
        }
    }

    private static void waitAllThreads(List<Thread> allThreadsList) throws InterruptedException {
        for (Thread thread : allThreadsList) {
            thread.join();
        }
    }
}
