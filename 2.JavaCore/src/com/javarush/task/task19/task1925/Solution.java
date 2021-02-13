package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
            StringBuilder sb = new StringBuilder();
            while(reader.ready()){
                sb.append(reader.readLine() + " ");
            }
            String input = sb.toString();
            String[] words = input.split("\\s+");
            String result = "";
            for (int i = 0; i < words.length; i++) {
                if(words[i].length() > 6){
                    result = result + words[i] + ",";
                }

            }
            String endResult = result.substring(0, result.length() - 1);
            writer.write(endResult);

            reader.close();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
