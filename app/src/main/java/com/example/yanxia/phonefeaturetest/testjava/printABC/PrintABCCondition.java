package com.example.yanxia.phonefeaturetest.testjava.printABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ---------------------
 * 作者：Angel_Zhl
 * 来源：CSDN
 * 原文：https://blog.csdn.net/qq_33915826/article/details/81205938
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class PrintABCCondition {
    private final Lock lock = new ReentrantLock();
    private Condition lockA = lock.newCondition();
    private Condition lockB = lock.newCondition();
    private Condition lockC = lock.newCondition();
    int flag = 0;


    public void printA() {
        lock.lock();
        try {
            while (true) {
                while (flag != 0)
                    lockA.await();
                System.out.print("A");
                flag = 1;
                lockB.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (true) {
                while (flag != 1)
                    lockB.await();
                System.out.print("B");
                flag = 2;
                lockC.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (true) {
                while (flag != 2)
                    lockC.await();
                System.out.print("C");
                Thread.sleep(1000);
                flag = 0;
                lockA.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintABCCondition printABC = new PrintABCCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                printABC.printA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                printABC.printB();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                printABC.printC();
            }
        }).start();
    }
}
