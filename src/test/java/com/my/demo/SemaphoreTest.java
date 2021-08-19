package com.my.demo;

import java.util.concurrent.Semaphore;

/**
 * @author hyl
 * @create 2021-01-28
 * @version: branch_member_20210108_v4_3
 */
public class SemaphoreTest {

    // 控制写操作的互斥信号量，初始值为 1
    private Semaphore wMutex = new Semaphore(1);
    // 控制对 rCount 读者计数器的互斥修改，初始值为 1
    private Semaphore rCountMutex = new Semaphore(1);
    // 正在进行读操作的读者个数，初始化为 0
    private int rCount = 0;

    public void write1() throws InterruptedException {
        while (true) {
            wMutex.acquire();// 进入临界区
            // write
            wMutex.release();// 离开临界区
        }
    }

    public void reader2() throws InterruptedException {
        while (true) {
            rCountMutex.acquire();// 进入临界区
            if (rCount == 0) {
                wMutex.acquire();
            }
            rCount++;
            rCountMutex.release();// 离开临界区

            // read

            rCountMutex.acquire();// 进入临界区
            rCount--;
            if (rCount == 0) {
                wMutex.release();// 最后一个读者离开，唤醒写着
            }
            rCountMutex.release();// 离开临界区
        }
    }
}
