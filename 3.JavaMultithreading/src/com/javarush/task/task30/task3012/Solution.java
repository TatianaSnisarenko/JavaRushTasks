package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.math.BigInteger;
import java.sql.Array;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);

        BigInteger x = new BigInteger("1048576");
        BigInteger result = x.multiply(x);
        System.out.println(result);

        BigInteger result2 = x.multiply(new BigInteger("16"));
        System.out.println(result2);

        BigInteger totalResult = result.multiply(result2);
        System.out.println(totalResult);
        System.out.println();
        System.out.println(result.multiply(new BigInteger("6")));
        System.out.println(result.multiply(new BigInteger("1")));
        System.out.println(result.multiply(new BigInteger("2")));
        System.out.println(result.multiply(new BigInteger("7")));
        System.out.println(result.multiply(new BigInteger("7")));
        System.out.println(result.multiply(new BigInteger("7")));
        System.out.println(result.multiply(new BigInteger("6")));
        System.out.println(result.multiply(new BigInteger("1")));

        BigInteger a = new BigInteger("726524996");
        BigInteger b = a.add(new BigInteger("873475104"));
        System.out.println(b);

        System.out.println(Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY);



    }

    public void createExpression(int number) {
        String result = "";

        ArrayList<Integer> giri = new ArrayList<>();
        for (int i = 1; i <= 2187; i = i * 3) {
            giri.add(i);
        }
        int x = number;
        int i = 0;
        while (true) {
            int ostatok = x % 3;
            if (ostatok == 0) {
                x = x / 3;
                if (x == 0) break;
                i++;
            } else if (ostatok == 1) {
                x = (x - 1) / 3;
                result = result + " + " + giri.get(i);
                if (x == 0) break;
                i++;
            } else if (ostatok == 2) {
                x = (x + 1) / 3;
                result = result + " - " + giri.get(i);
                if (x == 0) break;
                i++;
            }
        }
        System.out.println(number + " =" + result);
        //напишите тут ваш код
    }
}