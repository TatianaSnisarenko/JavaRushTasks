package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
       // StringWriter writer = getAllDataFromInputStream(new FileInputStream("D:\\result.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        writer.write("");
        if(is != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            char buffer[] = new char[1024];
            int len;
            int off = 0;
            while ((len = inputStreamReader.read(buffer)) > 0) {
                writer.write(buffer, off, len);
                off += len;
            }
        }

        return writer;
    }
}