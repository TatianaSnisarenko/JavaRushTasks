package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            double maxValue = -10000000.0;

            for(Map.Entry<String, Double> pair : map.entrySet()){
                String key = pair.getKey();
                Double value = pair.getValue();
                if(value > maxValue){
                    maxValue = value;
                }

            }

            String result = "";

            for(Map.Entry<String, Double> pair : map.entrySet()){
                String key = pair.getKey();
                Double value = pair.getValue();
                if(value == maxValue){
                    result = result + key + " ";
                }

            }
            System.out.println(result);

            reader.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
