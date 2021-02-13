package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try{
            reader = new BufferedReader(new FileReader(args[0]));
            writer = new BufferedWriter(new FileWriter(args[1]));
            ArrayList<String> strings = new ArrayList<>();
            while(reader.ready()){
                strings.add(reader.readLine());
            }
            String result = "";
            for(String s: strings){
                String [] strs = s.split(" ");
                for (int i = 0; i < strs.length; i++) {
                    if(strs[i].matches("\\b\\S+\\.*\\d+\\D*?\\b")){
                        result = result + strs[i] + " ";
                    }

                }
            }
            writer.write(result, 0, result.length() - 1);


        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                writer.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }
}
