package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        try{
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fileReader = new BufferedReader(new FileReader(consoleReader.readLine()));
            consoleReader.close();
            ArrayList<String> strings = new ArrayList<>();
            while(fileReader.ready()){
                strings.add(fileReader.readLine());
            }
            ArrayList<String> result = new ArrayList<>();
            for(String s: strings){
                StringBuilder sb = new StringBuilder();
                sb = sb.append(s);
                result.add(sb.reverse().toString());
            }
            for(String r: result){
                System.out.println(r);
            }

            fileReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
