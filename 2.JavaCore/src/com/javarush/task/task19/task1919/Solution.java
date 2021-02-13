package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            ArrayList<String> strings = new ArrayList<>();
            while(reader.ready()){
                strings.add(reader.readLine());

            }
            TreeMap<String, Double> map = new TreeMap<>();
            for(String s:strings){
                String[] strs = s.split(" ");
                map.put(strs[0], 0.0);
            }
            for(String s: strings){
                String[] strs = s.split(" ");
                Iterator<Map.Entry<String, Double>> iterator = map.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry<String, Double> pair = iterator.next();
                    String key = pair.getKey();
                    Double value = pair.getValue();
                    if(strs[0].equals(key)){
                        double count = value + Double.parseDouble(strs[1]);
                        map.put(key, count);
                    }

                }

            }

            for(Map.Entry<String, Double> pair : map.entrySet()){
                String key = pair.getKey();
                Double value = pair.getValue();
                System.out.println(key + " " + value);

            }

            reader.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
