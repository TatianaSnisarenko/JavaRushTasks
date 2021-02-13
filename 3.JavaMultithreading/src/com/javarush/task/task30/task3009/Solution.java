package com.javarush.task.task30.task3009;

/* 
Палиндром?
*/

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    private static Set<Integer> getRadix(String number){
        Set<Integer> radixes = new HashSet<>();
        if(checkString(number)){
            BigInteger a = new BigInteger(number);
            for (int i = 2; i <= 36; i++) {
                String s = a.toString(i);
                StringBuilder sb = new StringBuilder(s);
                if(s.equals(sb.reverse().toString())){
                    radixes.add(i);
                }
            }
        }

        return radixes;
    }

    public static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }
}