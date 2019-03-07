package com.example.yanxia.phonefeaturetest.testjava;

import java.util.concurrent.locks.ReentrantLock;

/**
 * https://www.jianshu.com/p/155260c8af6c
 * <p>
 * 最后的结果是 2000000；如果去掉锁，那么输出结果是一个小于2000000的不确定的数
 */
public class ReentrantLockTest extends Thread {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public ReentrantLockTest(String name) {
        super.setName(name);
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            lock.lock();
            try {
                System.out.println(this.getName() + " " + i);
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * @throws InterruptedException 正常操作
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest test1 = new ReentrantLockTest("thread1");
        ReentrantLockTest test2 = new ReentrantLockTest("thread2");

        test1.start();
        test2.start();
        test1.join();
        test2.join();
        System.out.println(i);
    }
}
