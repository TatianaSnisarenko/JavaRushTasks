package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    public static int threadCount = Thread.MIN_PRIORITY;

        {
            if (threadCount > Thread.MAX_PRIORITY) threadCount = Thread.MIN_PRIORITY;
            setPriority(threadCount++);
        }




    public MyThread() {

    }

    public MyThread(Runnable target) {
        super(target);

    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);

    }

    public MyThread(String name) {
        super(name);

    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);

    }

    public MyThread(Runnable target, String name) {
        super(target, name);

    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);

    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);

    }

    /*public MyThread(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {
        super(group, target, name, stackSize, inheritThreadLocals);

    }*/


}
