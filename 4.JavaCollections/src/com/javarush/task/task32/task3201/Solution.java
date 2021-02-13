package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        byte bytes [] = text.getBytes();


        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
            if(number < raf.length()){
                raf.seek(number);
            }else{
                raf.seek(raf.length());
            }
            raf.write(bytes);
            //raf.writeBytes(text);
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
