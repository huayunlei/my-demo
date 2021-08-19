package com.my.demo.test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.swagger.models.auth.In;

import java.util.concurrent.*;

/**
 * @author hyl
 * @create 2021-02-02
 * @version: branch_member_20210108_v4_3
 */
public class ThreadTest {

    static class StatsJob implements Runnable {

        private Integer centerId;

        public StatsJob (Integer centerId) {
            this.centerId = centerId;
        }

        @Override
        public void run() {
            System.out.println(centerId);
            int a = 2 / 0;
            System.out.println(a);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 线程工厂，用于为线程池中的每条线程命名
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("stats-pool-%d")
                .setUncaughtExceptionHandler((thread, throwable) -> System.out.println(String.format("ThreadPool %s got exception: %s", thread, throwable))).build();

        // 创建线程池，使用有界阻塞队列防止内存溢出
        ExecutorService statsThreadPool = new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100), namedThreadFactory);
        // 遍历所有中心，为每一个centerId提交一条任务到线程池
        Future future = statsThreadPool.submit(new StatsJob(1));
//        future.get();
//        System.out.println("end");
//        statsThreadPool.execute(new StatsJob(1));

    }

}
