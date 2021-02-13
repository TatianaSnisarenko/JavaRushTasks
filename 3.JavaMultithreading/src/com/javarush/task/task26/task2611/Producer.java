package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;
    private final AtomicInteger counter = new AtomicInteger();

    public Producer(ConcurrentHashMap<String, String> map) {
        this.map = map;

    }

    @Override
    public void run() {
        try{
            int i = 1;
            while (true){
                i = counter.incrementAndGet();
                String index = String.valueOf(i);
                map.putIfAbsent(index, "Some text for " + index);
                                //System.out.println(map);
                Thread.sleep(500);
            }

        }catch (InterruptedException e){
            System.out.println(String.format("[%s] thread was terminated", Thread.currentThread().getName()));
        }
    }
}
