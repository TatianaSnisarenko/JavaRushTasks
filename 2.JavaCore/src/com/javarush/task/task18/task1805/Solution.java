package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        reader.close();
        HashSet<Integer> set = new HashSet<>();
        while(inputStream.available() > 0){
            int data = inputStream.read();
            set.add(data);


        }
        inputStream.close();

        TreeSet<Integer> tset = new TreeSet<>();
        for(Integer d : set){
            tset.add(d);
        }

        String s = "";
        for(Integer td : tset){
            s = s + td + " ";
        }
        System.out.println(s);

    }
}
