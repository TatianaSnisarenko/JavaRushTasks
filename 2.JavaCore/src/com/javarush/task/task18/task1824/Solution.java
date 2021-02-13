package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
Читайте с консоли имена файлов.
Если файла не существует (передано неправильное имя файла), то перехватить исключение FileNotFoundException, вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки.
Не используйте System.exit();
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        String fileName ="";
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                fileName = reader.readLine();
                try(FileInputStream fis = new FileInputStream(fileName)) {

                } catch (FileNotFoundException e) {
                    System.out.println(fileName);
                    reader.close();
                    break;
                }

            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
