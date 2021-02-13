package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileReader fileReader = new FileReader(reader.readLine());
            reader.close();
            ArrayList<Character> characters = new ArrayList<>();
            while (fileReader.ready()) {
                int data = fileReader.read();
                characters.add((char) data);

            }
            fileReader.close();
            StringBuilder sb = new StringBuilder();
            for(Character c: characters){
                sb.append(c);
            }
            String line = sb.toString();

            Pattern pattern = Pattern.compile("\\s*(\\s|,|!|\\.|\\?|\\.\\.\\.|$|\\-|:|;|\"|\\[\\])\\s*");
            String[]words = pattern.split(line);

            int count = 0;
            for (int i = 0; i < words.length; i++) {
                if(words[i].equals("world")){
                    count++;
                }

            }

            System.out.println(count);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
