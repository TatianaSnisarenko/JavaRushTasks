package com.javarush.task.task30.task3004;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int x;
    public BinaryRepresentationTask(int i) {
        x = i;
    }

    @Override
    protected String compute() {
        if (x==0)
            return "";
        int a = x % 2;
        int b = x / 2;
        if(b <= 0 ) return String.valueOf(a);
        else {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            return task.join() + String.valueOf(a);
        }



    }
}
