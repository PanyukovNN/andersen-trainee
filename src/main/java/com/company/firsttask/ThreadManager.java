package com.company.firsttask;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadManager {

    private Queue<Thread> threads = new ConcurrentLinkedQueue<>();

    private synchronized void pollThread() throws InterruptedException {
        while (threads.size() >= TestConsts.MAX_THREADS) {
            for (Thread thread : threads) {
                if (!thread.isAlive()) {
                    threads.remove(thread);
                    return;
                }
            }
            Thread.sleep(10);
        }
    }

    public synchronized void addThreadToQueue(Thread thread) throws InterruptedException {
        if (threads.size() >= TestConsts.MAX_THREADS) {
            pollThread();
        }
        threads.add(thread);
    }
}
