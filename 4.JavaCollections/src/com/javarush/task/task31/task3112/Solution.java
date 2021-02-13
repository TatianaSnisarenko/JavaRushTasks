package com.javarush.task.task31.task3112;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    //Метод должен создать объект URL и загрузить содержимое файла на локальный диск.
    //Выкачивай сначала во временную директорию, чтобы в случае неуспешной загрузки в твоей директории не оставались недокачанные файлы.
    //Затем перемести файл в пользовательскую директорию. Имя для файла возьми из ссылки.
    //Используй только классы и методы из пакета java.nio.

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();
        Path tempFile = Files.createTempFile("temp -", ".tmp");
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        String fileName = new File(urlString).getName();

        String filePath = downloadDirectory.toString() + File.separator + fileName;

        Path directories = Files.createDirectories(downloadDirectory);
        Path fileDestination = null;
        if(Files.notExists(Paths.get(filePath))) {
            fileDestination = Files.createFile(Paths.get(filePath));
        }else{
            fileDestination = Paths.get(filePath);
        }
        Files.move(tempFile,fileDestination, StandardCopyOption.REPLACE_EXISTING);
        return fileDestination;
        // implement this method
    }
}
