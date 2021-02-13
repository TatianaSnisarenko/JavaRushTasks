package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fName1 = reader.readLine();
            String fName2 = reader.readLine();
            String fName3 = reader.readLine();
            reader.close();
            BufferedInputStream bif2 = new BufferedInputStream(new FileInputStream(fName2));
            BufferedInputStream bif3 = new BufferedInputStream(new FileInputStream(fName3));
            BufferedOutputStream bof1 = new BufferedOutputStream(new FileOutputStream(fName1));
            BufferedOutputStream bof2 = new BufferedOutputStream(new FileOutputStream(fName1, true));
            int i;
            while (( i = bif2.read()) != -1){
                bof1.write(i);
            }
            int b;
            while((b = bif3.read()) != -1){
                bof2.write(b);
            }
            bif2.close();
            bif3.close();
            bof1.close();
            bof2.close();



        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
