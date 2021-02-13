package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.Collections;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();

        ArrayList<String> list = new ArrayList<>();

        while (e != null) {
            list.add(e.toString());
            e = e.getCause();
        }

        Collections.reverse(list);
        for (String s : list){
            System.out.println(s);
        }

    }
    public static void main(String[] args) throws Exception {
        Thread.currentThread().setUncaughtExceptionHandler(new Solution());
        throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));

    }
}
