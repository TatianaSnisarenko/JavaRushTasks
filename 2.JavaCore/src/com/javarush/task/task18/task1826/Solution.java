package com.javarush.task.task18.task1826;

/* 
Шифровка
2. Создай поток для чтения из файла, который приходит вторым параметром ([fileName]).
3. Создай поток для записи в файл, который приходит третьим параметром ([fileOutputName]).
4. В режиме "-e" программа должна зашифровать [fileName] и записать в [fileOutputName].
5. В режиме "-d" программа должна расшифровать [fileName] и записать в [fileOutputName].
6. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        try{
            FileInputStream fis = new FileInputStream(args[1]);
            FileOutputStream fos = new FileOutputStream(args[2]);
            byte[] source = new byte[fis.available()];
            fis.read(source);
            byte key = '5';
            if(args[0].equals("-e")){
                for (int i = 0; i < source.length; i++) {
                    source[i] = (byte) (source[i] ^ key);

                }
                

            }else if(args[0].equals("-d")){
                for (int i = 0; i < source.length; i++) {
                    source[i] = (byte) (source[i] ^ key);

                }

            }
            for (int i = 0; i < source.length; i++) {
                fos.write(source[i]);
            }
            fis.close();
            fos.close();

        }catch (IOException e){
            e.printStackTrace();
        }


    }

}
