package com.example.libjava.thread;

public class ThreadVariableStopDemo {
    public static void main(String[] args) throws InterruptedException {
        // stopThreadUseMark();
        stopThreadByInterrupt();
    }

    private static void stopThreadUseMark() throws InterruptedException {
        VariableStopThread thread = new VariableStopThread("thread_1");
        thread.start();
        Thread.sleep(1);
        thread.Stop();
    }

    private static void stopThreadByInterrupt() throws InterruptedException {
        Thread thread = new InterruptThread("thread_1");
        thread.start();
        Thread.sleep(1);
        System.out.println(thread.getName() + "线程设置:interrupt");
        thread.interrupt();
    }

}

class VariableStopThread extends Thread {
    private boolean interrupt = true;

    public VariableStopThread(String name) {
        super(name);
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + ":线程开始运行!");
        int i = 0;
        while (interrupt) {
            System.out.println("" + (i++));
        }
        System.out.println("我停止了! timer:" + System.currentTimeMillis());
    }

    public void Stop() {
        System.out.println(Thread.currentThread().getName() + ":线程设置了停止! timer:" + System.currentTimeMillis());
        this.interrupt = false;
    }
}

class InterruptThread extends Thread {

    public InterruptThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始!");
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(0);
                System.out.println("" + (i + 1));
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "线程捕获异常,退出循环!");
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + "线程结束!");
    }
}
