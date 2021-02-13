package com.javarush.task.task18.task1825;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        String fileName;

        while (!(fileName = bufferedReader.readLine()).equals("end")) {
            list.add(fileName);
        }

        bufferedReader.close();
        Collections.sort(list);

        String[] stringArray = list.get(0).split(".part");
        String summaryFileName = stringArray[0];

        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = new FileOutputStream(summaryFileName, true);
        byte[] buffer;

        for (String aList : list) {
            fileInputStream = new FileInputStream(aList);
            buffer = new byte[fileInputStream.available()];
            //noinspection ResultOfMethodCallIgnored
            fileInputStream.read(buffer);
            fileInputStream.close();
            fileOutputStream.write(buffer);
        }

        fileOutputStream.close();
    }
}