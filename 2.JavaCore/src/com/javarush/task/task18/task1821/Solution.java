package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете).

Пример вывода:
, 19
- 7
f 361
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            StringBuilder sb = new StringBuilder();
            ArrayList<Integer> ints = new ArrayList<>();
            while (reader.ready()){
                ints.add(reader.read());
            }

            char[] chars = new char[ints.size()];
            for (int i = 0; i < ints.size(); i++) {
                int n = ints.get(i);
                chars[i] = (char) n;

            }


            Arrays.sort(chars);


            TreeMap<Character, Integer> tmap = new TreeMap<>();
            for(char c:chars){
                tmap.put(c, 0);
            }


            Iterator<Map.Entry<Character, Integer>> iterator = tmap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<Character, Integer> pair = iterator.next();
                Character key = pair.getKey();
                Integer value = pair.getValue();
                int count = value;
                for (Character c: chars) {
                    if(key == c){
                        count++;
                    }
                }
            tmap.put(key, count);

            }


            for(Map.Entry<Character, Integer> pair: tmap.entrySet()){
                Character key = pair.getKey();
                Integer value = pair.getValue();
                System.out.println(key + " " + value);
            }
            reader.close();





        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
