package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream inputStream = new FileInputStream(reader.readLine());
            FileOutputStream outputStream = new FileOutputStream(reader.readLine());
            reader.close();
            byte[] buffer = new byte[inputStream.available()];
            if(inputStream.available() > 0){
                inputStream.read(buffer);
                byte[] copy = new byte[buffer.length];
                int j = buffer.length - 1;
                for (int i = 0; i < buffer.length; i++) {
                     copy[j] = buffer[i];
                     j--;
                    }


                outputStream.write(copy, 0, copy.length);

            }
            inputStream.close();
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
