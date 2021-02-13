package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);
    static{
        threads.add(new ThreadOne());
        threads.add(new ThreadTwo());
        threads.add(new ThreadThree());
        threads.add(new ThreadFour());
        threads.add(new ThreadFive());

    }

    public static void main(String[] args) {
    }

    public static class ThreadOne extends Thread {
        public void run() {
            while (!isInterrupted()) {

            }
        }
    }

    public static class ThreadTwo extends Thread{
        public void run(){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
            }
    }

    public static class ThreadThree extends Thread{
        public void run() {
            while (!interrupted()) {
                try {

                    System.out.println("Ура");
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ThreadFour extends Thread implements Message {
        public void showWarning() {
            this.interrupt();
        }

        public void run() {
            while (!isInterrupted()) {
            }
        }
    }

    public static class ThreadFive extends Thread{
        private static String str;
        private static int sum;
        public void run() {
            List <Integer>list = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
                while(true) {
                    str = reader.readLine();
                    if(str != "N") {
                        int num = Integer.parseInt(str);
                        list.add(num);
                    }
                }
            } catch (IOException e) {}
            catch (NumberFormatException n) {
                for(int in : list) {
                    sum += in;
                }
            } System.out.println(sum);
        }
    }
}