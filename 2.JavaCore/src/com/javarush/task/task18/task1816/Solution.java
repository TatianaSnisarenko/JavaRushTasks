package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {

        int count = 0;

        try {
            FileInputStream fis = new FileInputStream(args[0]);

            while(fis.available() > 0){
                int b = fis.read();
                if(b > 64 && b < 91 || b > 96 && b < 123){
                    count++;
                }
            }


            fis.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);

    }
}
