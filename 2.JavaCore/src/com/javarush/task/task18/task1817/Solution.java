package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        int count = 0;
        int spaces = 0;
        double result = 0;

        try {
            FileInputStream fis = new FileInputStream(args[0]);
            while(fis.available() > 0){


                if((byte)fis.read() == ' '){
                    spaces++;
                }
                count++;

            }
            fis.close();
            if(count != 0){
                result = (spaces * 1.0 / count) * 100;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.printf("%.2f", result);

    }
}
