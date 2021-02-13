package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл из частей архива
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        List<String> files = new ArrayList<>();
        Collections.addAll(files, args);
        files.remove(files.get(0));
        Collections.sort(files);
        Vector<InputStream> v = new Vector<>();
        for (String s:files) {
            v.add(new FileInputStream(s));
        }

        try (
                ZipInputStream zis = new ZipInputStream(new SequenceInputStream(v.elements()));
                FileOutputStream fos = new FileOutputStream(args[0])){
            byte[] bytes = new byte[1024];
            ZipEntry ze;
            int len = 0;

            while ((ze = zis.getNextEntry()) != null) {
                while ((len = zis.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
            }
        }
    }
}





