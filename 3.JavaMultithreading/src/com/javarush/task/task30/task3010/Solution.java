package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    private static String getMinRadix(String s){
        String result = "incorrect";
        ArrayList<Integer> radixes = new ArrayList<>();
        for (int i = 2; i <= 36; i++) {
            try {
                BigInteger a = new BigInteger(s, i);
                radixes.add(i);
            } catch (Exception e) {

            }
        }
        if(radixes.size() != 0){
            result = Collections.min(radixes).toString();
            //System.out.println(radixes);

        }
        return result;
    }
    public static void main(String[] args) {
        //напишите тут ваш код
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String s = null;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            System.out.println("incorrect");
        }*/
        try {
            System.out.println(getMinRadix(args[0]));
        }catch (Exception e){

        }
        /*System.out.println(getMinRadix("00"));
        System.out.println(getMinRadix("12AS08z"));
        System.out.println(getMinRadix("12AS08Z/"));
        System.out.println(getMinRadix("0"));*/



    }
}