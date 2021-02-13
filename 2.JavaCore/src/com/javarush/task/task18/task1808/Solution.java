package com.javarush.task.task18.task1808;

/* 
Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать большую часть.
Закрыть потоки.


*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream inputStream = new FileInputStream(reader.readLine());
            FileOutputStream outputStream1 = new FileOutputStream(reader.readLine());
            FileOutputStream outputStream2 = new FileOutputStream(reader.readLine());
            reader.close();

            byte[] buffer = new byte[inputStream.available()];
            int count = 0;
            if(inputStream.available() > 0){
                count = inputStream.read(buffer);

                if(count % 2 == 0) {
                    outputStream1.write(buffer, 0, count / 2);
                    outputStream2.write(buffer, count / 2, count / 2);
                }else{
                    outputStream1.write(buffer, 0, count / 2 + 1);
                    outputStream2.write(buffer, count / 2 + 1, count / 2);
                }




            }
            inputStream.close();
            outputStream1.close();
            outputStream2.close();


        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
