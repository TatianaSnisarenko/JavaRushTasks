package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;

/* 
Древний Рим
{I, V, X, L, C, D, M}
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] chars = s.toCharArray();
        System.out.println(Arrays.toString(chars));
        TreeMap<Character, Integer> map = new TreeMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        map.put('i', 1);
        map.put('v', 5);
        map.put('x', 10);
        map.put('l', 50);
        map.put('c', 100);
        map.put('d', 500);
        map.put('m', 1000);
        int number = map.get(chars[chars.length - 1]);

        for (int i = chars.length - 1; i > 0 ; i--) {
            int right = map.get(chars[i]);
            int left = map.get(chars[i - 1]);
            if(right > left){
                number -= left;
            }else{
                number += left;

            }

        }
        return number;


    }

}
