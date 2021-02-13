package com.javarush.task.task39.task3904;

/*
Лестница
*/

public class Solution {
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if(n <  0 ) return 0l;

        long a1 = 1l;
        long a2 = 2l;
        long a3 = 4l;
        long t  = 0l;

        switch (n){
            case 0:
            case 1: return 1l;
            case 2: return 2l;
            case 3: return 4l;
            default:
                for(int i = 4; i < n; i++) {
                    t = a1 + a2 + a3;
                    a1 = a2;
                    a2 = a3;
                    a3 = t;
                }
                return a1 + a2 + a3;
        }
    }
}


