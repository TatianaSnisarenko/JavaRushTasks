package com.javarush.task.task18.task1819;

/* 
Объединение файлов
Считать с консоли 2 имени файла.
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов.
Закрыть потоки.


*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fName1 = reader.readLine();
            String fName2 = reader.readLine();
            reader.close();





            FileInputStream fis1 = new FileInputStream(fName1);
            byte [] file1 = new byte[fis1.available()];
            fis1.read(file1, 0, file1.length);



            FileInputStream fis2 = new FileInputStream(fName2);
            byte[] file2 = new byte[fis2.available()];
            fis2.read(file2, 0 , file2.length);

            byte[] result = new byte[file2.length + file1.length];

            System.arraycopy(file2, 0, result, 0, file2.length);
            System.arraycopy(file1, 0, result, file2.length, file1.length);

            FileOutputStream fos = new FileOutputStream(fName1);
            fos.write(result);


            fis1.close();
            fis2.close();
            fos.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
