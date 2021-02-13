package com.javarush.task.task19.task1908;

/* 
Выделяем числа
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй BufferedReader c конструктором принимающим FileReader).
4. Поток чтения из файла (BufferedReader) должен быть закрыт.
5. Программа должна записывать во второй файл все числа, через пробел, из первого файла (используй BufferedWriter с конструктором FileWriter).
6. Поток записи в файл (BufferedWriter) должен быть закрыт.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(reader.readLine()));
            reader.close();
            StringBuilder sb = new StringBuilder();
            while(fileReader.ready()){
                sb.append(fileReader.readLine());
            }
            String line = sb.toString();

           line.replaceAll("\\p{Punct}", "");
           String [] strs = line.split("\\s+");

           String result = "";
            for (int i = 0; i < strs.length; i++) {
                if(strs[i].matches("\\b\\d+\\b")){
                    result = result + strs[i] + " ";
                }
            }
            fileWriter.write(result);

            fileReader.close();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
