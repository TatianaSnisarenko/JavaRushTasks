package com.javarush.task.task18.task1823;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();


    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String s = reader.readLine();
                if(s.equals("exit")){

                    break;
                }else{
                    new ReadThread(s).start();
                }
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public static class ReadThread extends Thread {
        private FileInputStream fis;
        private String fileName;
        public ReadThread(String fileName) {
            this.fileName = fileName;

        }
        public void run(){
            ArrayList<Integer> data = new ArrayList<>();
            HashMap<Integer, Integer> map = new HashMap<>();
            Integer maxKey = -1;
            Integer maxValue = -1;
            try {
                FileInputStream fis = new FileInputStream(fileName);
                while (fis.available() > 0) {
                    data.add(fis.read());
                }
                for (Integer i : data) {
                    map.put(i, 0);
                }
                fis.close();

                Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Integer> pair = iterator.next();
                    Integer key = pair.getKey();
                    Integer value = pair.getValue();
                    for (Integer n : data) {
                        if (key == n) {
                            map.put(key, value + 1);
                        }
                    }

                }

                for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
                    Integer key = pair.getKey();
                    Integer value = pair.getValue();
                    if (value > maxValue) {
                        maxValue = value;
                        maxKey = key;
                    }

                }
            }catch(IOException e){
                e.printStackTrace();
            }
            resultMap.put(fileName, maxKey);




        }
        // implement file reading here - реализуйте чтение из файла тут
    }
}
