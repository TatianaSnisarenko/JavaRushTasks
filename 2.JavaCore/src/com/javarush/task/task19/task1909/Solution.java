package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(reader.readLine()));
            reader.close();
            StringBuilder sb = new StringBuilder();
            while(fileReader.ready()){
                sb.append(fileReader.readLine());
            }
            String line = sb.toString();

            String result = line.replaceAll("\\.", "!");

            fileWriter.write(result);

            fileReader.close();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
