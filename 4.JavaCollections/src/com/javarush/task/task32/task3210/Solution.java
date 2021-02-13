package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        int len = text.length();


        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
            raf.seek(number);

            byte b[] = new byte[len];
            raf.read(b, 0, len);
            String newText = new String(b);
            raf.seek(raf.length());
            if(text.equals(newText)){
                raf.write("true".getBytes());
            }else{
                raf.write("false".getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
