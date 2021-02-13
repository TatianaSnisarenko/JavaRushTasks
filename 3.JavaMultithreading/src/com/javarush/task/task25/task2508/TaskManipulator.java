package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator{
    private Thread t;
    @Override
    public void run() {
        Thread thread  = Thread.currentThread();
        while(!thread.isInterrupted()) {
            System.out.println(t.getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //t.interrupt();
                break;
            }
        }
    }

    @Override
    public void start(String threadName) {
        t = new Thread(this, threadName);
        t.start();
    }

    @Override
    public void stop() {
      t.interrupt();
    }
}
