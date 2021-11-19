package com.cmc.main;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池测试
 */
public class ThreadPoolMain {
    /**
     * 线程池测试
     */
    private static final AtomicInteger THREAD_EXECUTED_TOTAL = new AtomicInteger(0); // 已执行线程总数
    private static final AtomicLong EXECUTE_COST_MS = new AtomicLong(0); // 执行耗时毫秒
    private static final Integer ACCUMULATED_SUM_UPPER = 1000000; // 单个任务累加和上限
    private static final Integer TASK_TOTAL = 1000; // 任务总计

    @Test
    public void testRunWithoutThreadPool() {
        List<Thread> tList = new ArrayList<Thread>(TASK_TOTAL);
        for (int i = 0; i < TASK_TOTAL; i++) {
            tList.add(new Thread(new IncreaseThread()));
        }
        for (Thread t : tList) {
            t.start();
        }
        for (;;);
    }

    @Test
    public void testRunWithThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 100, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue());
        for (int i = 0; i < TASK_TOTAL; i++) {
            executor.submit(new IncreaseThread());
        }
        for (;;);
    }

    private class IncreaseThread implements Runnable {
        public void run() {
            long startTime = System.currentTimeMillis();

            AtomicInteger counter = new AtomicInteger(0);
            for (int i = 0; i < ACCUMULATED_SUM_UPPER; i++) {
                counter.incrementAndGet();
            }
            // 累加执行时间
            EXECUTE_COST_MS.addAndGet(System.currentTimeMillis() - startTime);
            if (THREAD_EXECUTED_TOTAL.incrementAndGet() == TASK_TOTAL) {
                System.out.println("cost: " + EXECUTE_COST_MS.get() + "ms");
            }
        }
    }
}
