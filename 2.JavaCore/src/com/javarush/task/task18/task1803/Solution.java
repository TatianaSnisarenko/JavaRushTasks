package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Самые частые байты
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль через пробел должны выводиться все байты из файла с максимальным количеством повторов.
4. Данные в консоль должны выводится в одну строку.
5. Поток чтения из файла должен быть закрыт.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(s);
       ArrayList<Integer> datas= new ArrayList<>();
        while(inputStream.available() > 0){
            int data = inputStream.read();
            datas.add(data);
            }
        inputStream.close();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(Integer d: datas){
            map.put(d, 1);
    }
        int count = 1;
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, Integer> pair = iterator.next();
            Integer key = pair.getKey();
            for(Integer d: datas){
                if(d == key){
                    count++;
                }
            }
            map.put(key,count);
            count = 1;

        }
        int maxCount = 0;
       Set<Integer> maxKeys = new HashSet<>();
        for(Map.Entry<Integer, Integer> pair : map.entrySet()){
            Integer value = pair.getValue();
            if(value > maxCount){
                maxCount = value;

            }
        }

        for(Map.Entry<Integer, Integer> pair : map.entrySet()){
            Integer key = pair.getKey();
            Integer value = pair.getValue();
            if(value == maxCount){

               maxKeys.add(key);
            }
        }
        String str = "";
        for(Integer maxKey: maxKeys) {



                    str = str + maxKey + " ";




        }
        System.out.println(str);


    }
}
