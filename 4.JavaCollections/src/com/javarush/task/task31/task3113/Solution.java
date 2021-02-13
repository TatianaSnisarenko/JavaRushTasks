package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution extends SimpleFileVisitor<Path> {
    long countOfFiles = 0;
    long countOfDirectories = 0;
    long size = 0;

    public long getSize() {
        return size;
    }

    public long getCountOfFiles() {
        return countOfFiles;
    }

    public long getCountOfDirectories() {
        return countOfDirectories;
    }


    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if(attrs.isDirectory()) countOfDirectories++;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        countOfFiles++;
        size += attrs.size();

        return FileVisitResult.CONTINUE;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        //long size = Files.size(Paths.get(line));

        if (!Files.isDirectory(Paths.get(line))) {
            System.out.println(Paths.get(line).toAbsolutePath() + " - не папка");
        }else {
            Solution solution = new Solution();
            Files.walkFileTree(Paths.get(line), solution);
            //Всего папок - [количество папок в директории и поддиректориях]
            //Всего файлов - [количество файлов в директории и поддиректориях]
            //Общий размер - [общее количество байт, которое хранится в директории]

            long countOfDirectories = solution.getCountOfDirectories() - 1;
            System.out.println("Всего папок - " + countOfDirectories);
            System.out.println("Всего файлов - " + solution.getCountOfFiles());
            System.out.println("Общий размер - " + solution.getSize());




        }
    }
}
