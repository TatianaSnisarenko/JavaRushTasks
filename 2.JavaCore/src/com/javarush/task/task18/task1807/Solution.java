package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        int count = 0;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream inputStream = new FileInputStream(reader.readLine());
            reader.close();

            while(inputStream.available() > 0){
                int data = inputStream.read();
                char c = ',';
                if((byte) data == c){
                    count++;
                }
            }
            inputStream.close();
            System.out.println(count);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
