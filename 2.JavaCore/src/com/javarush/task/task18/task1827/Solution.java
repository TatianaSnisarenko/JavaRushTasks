package com.javarush.task.task18.task1827;

/* 
Прайсы
где id - 8 символов.
productName - название товара, 30 символов.
price - цена, 8 символов.
quantity - количество, 4 символа.
-c - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле.
В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины.
Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws Exception {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();
            if(args.length > 0) {
                if(args[0].equals("-c")){
                    BufferedReader fReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                    ArrayList<String> strings = new ArrayList<>();
                    while(fReader.ready()){
                        strings.add(fReader.readLine());
                    }
                    ArrayList<Integer> ids = new ArrayList<>();
                    for(String s: strings){
                        ids.add(Integer.parseInt(s.substring(0,8).trim()));
                    }
                    int maxId = Collections.max(ids) + 1;

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)));
                    String result = "%n%-8d%-30s%-8.2f%-4d";

                    writer.write(String.format(result,maxId,args[1],Double.parseDouble(args[2]),Integer.parseInt(args[3])));
                    fReader.close();
                    writer.close();
                }
            }
        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
