package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader freader = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));
            reader.close();
            ArrayList<String> strings = new ArrayList<>();
            while(freader.ready()){
                strings.add(freader.readLine());
            }

            for(String s: strings){
                String [] strs = s.split(" ");
                int i = Integer.parseInt(strs[0]);
                int k = Integer.parseInt(args[0]);
                if(i == k){
                    result = s;
                }

            }
            freader.close();

        }catch (IOException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        System.out.println(result);



    }
}
