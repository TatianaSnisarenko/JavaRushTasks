package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    static{
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        BufferedReader consoleReader = null;
        BufferedReader reader = null;

        try{
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = consoleReader.readLine();
            reader = new BufferedReader(new FileReader(fileName));

            ArrayList<String> strings = new ArrayList<>();
            while(reader.ready()){
                strings.add(reader.readLine());
            }

            ArrayList<String> result = new ArrayList<>();
           for(String s: strings){
               String line ="";

               for(Map.Entry<Integer, String> pair: map.entrySet()){
                   Integer key = pair.getKey();
                   String value = pair.getValue();
                   line = s.replaceAll("(\\b" + key + "\\b)", value);
                   s = line;

               }
               result.add(line);


           }
           for(String s: result){
               System.out.println(s);
           }



        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                consoleReader.close();
                reader.close();

            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }
}
