package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread t;
    Thread.State previosState;

    public LoggingStateThread(Thread t) {
        this.t = t;
        previosState = t.getState();


    }

    public void run(){
        System.out.println(previosState);
        while(true){
            Thread.State currentState = t.getState();
            if(previosState != currentState){
                System.out.println(currentState);
                previosState = currentState;
            }
            if(previosState == State.TERMINATED)
                break;

        }
        interrupt();


    }
}
