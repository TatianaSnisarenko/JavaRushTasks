package com.javarush.task.task18.task1820;

/* 
Округление чисел
Считать с консоли 2 имени файла.
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415.
Округлить числа до целых и записать через пробел во второй файл.
Закрыть потоки.

Принцип округления:
3.49 => 3
3.50 => 4

*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fReader = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));
            BufferedWriter fWriter = new BufferedWriter(new OutputStreamWriter((new FileOutputStream(reader.readLine()))));
            reader.close();

            StringBuilder sb = new StringBuilder();
            while(fReader.ready()){
                sb.append(fReader.readLine());
            }
            String data = sb.toString();



            String[] strings = data.split(" ");
            ArrayList<Double> doubles = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {


            try{
                doubles.add(Double.parseDouble(strings[i]));
            }catch(NumberFormatException e){
                e.printStackTrace();
            }
            }
            String result = "";
            for(Double d: doubles){
                long n = Math.round(d);
                result =result +  n + " ";

            }
            fWriter.write(result, 0, result.length());

            fReader.close();
            fWriter.close();




        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
