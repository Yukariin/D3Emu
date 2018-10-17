package com.d3emu.game;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolManager {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolManager.class);

    private static ThreadPoolManager instance;
    
    private final ThreadPoolExecutor generalThreadPool;
    private final ThreadPoolExecutor packetsThreadPool;
    private ScheduledThreadPoolExecutor generalScheduledThreadPool;

    private ThreadPoolManager() {
        generalThreadPool = new ThreadPoolExecutor(4, 4 + 2, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        packetsThreadPool = new ThreadPoolExecutor(2, 2 + 2, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        generalScheduledThreadPool = new ScheduledThreadPoolExecutor(5);
    }

    public void executeGeneral(Runnable task) {
        try {
            generalThreadPool.execute(task);
        } catch (RejectedExecutionException e) {
            // ignore
        }
    }

    public void executePacket(Runnable task) {
        try {
            packetsThreadPool.execute(task);
        } catch (RejectedExecutionException e) {
            // ignore
        }
    }

    public ScheduledFuture<?> scheduleGeneral(Runnable task, long delay) {
        return scheduleGeneral(task, delay, TimeUnit.MILLISECONDS);
    }

    public ScheduledFuture<?> scheduleGeneral(Runnable task, long delay, TimeUnit unit) {
        try {
            return generalScheduledThreadPool.schedule(task, delay, unit);
        } catch (RejectedExecutionException e) {
            return null;  // ignore
        }
    }
    
    public ScheduledFuture<?> scheduleGeneralAtFixedRate(Runnable task, long initialDelay, long period) {
        return scheduleGeneralAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    public ScheduledFuture<?> scheduleGeneralAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        try {
            return generalScheduledThreadPool.scheduleAtFixedRate(task, initialDelay, period, unit);
        } catch (RejectedExecutionException e) {
            return null;  // ignore
        }
    }
    
    public void shutdown() {
        try {
            generalThreadPool.awaitTermination(1, TimeUnit.SECONDS);
            packetsThreadPool.awaitTermination(1, TimeUnit.SECONDS);
            generalScheduledThreadPool.awaitTermination(1, TimeUnit.SECONDS);

            generalThreadPool.shutdown();
            packetsThreadPool.shutdown();
            generalScheduledThreadPool.shutdown();

            logger.info("All ThreadPools are now stopped");
        } catch (InterruptedException e) {
            logger.warn("", e);
        }
    }

    public void purge() {
        generalThreadPool.purge();
        packetsThreadPool.purge();
        generalScheduledThreadPool.purge();
    }

    public static synchronized ThreadPoolManager getInstance() {
        if (instance == null) {
            instance = new ThreadPoolManager();
        }
        return instance;
    }
}
