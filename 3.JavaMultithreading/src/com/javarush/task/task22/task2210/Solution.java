package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

        //System.out.println(Arrays.toString(getTokens("level22.lesson13.task01", ".")));


    }
    public static String [] getTokens(String query, String delimiter) {
        ArrayList<String> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            result.add(token);
        }
        String[] resultArray = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
         resultArray[i] = result.get(i);
        }
        return resultArray;
    }
}
