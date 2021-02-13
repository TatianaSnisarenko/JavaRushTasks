package com.javarush.task.task18.task1828;

/* 
Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD

Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id

Значения параметров:
где id - 8 символов
productName - название товара, 30 символов
price - цена, 8 символов
quantity - количество, 4 символа
-u - обновляет данные товара с заданным id
-d - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19847   Шорты пляжные синие           159.00  12
198479  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234


*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();
            if(args.length > 0) {


                BufferedReader fReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                ArrayList<String> strings = new ArrayList<>();
                while(fReader.ready()){
                    strings.add(fReader.readLine());
                }
                ArrayList<Integer> ids = new ArrayList<>();
                for(String s: strings){
                    ids.add(Integer.parseInt(s.substring(0,8).trim()));
                }

                LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
                for (int i = 0; i < strings.size() ; i++) {
                    map.put(ids.get(i), strings.get(i));
                }
                if(args[0].equals("-d")) {
                    map.remove(Integer.parseInt(args[1]));
                }else if( args[0].equals("-u")){
                    int index = Integer.parseInt(args[1]);
                    String result = String.format("%-8d%-30s%-8.2f%-4d",Integer.parseInt(args[1]),args[2],Double.parseDouble(args[3]),Integer.parseInt(args[4]));
                    map.put(index, result);
                }

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
                for(Map.Entry<Integer,String> pair : map.entrySet()){
                    String value = pair.getValue();
                    writer.write(value + "\n");
                }
                fReader.close();
                writer.close();

            }
        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
