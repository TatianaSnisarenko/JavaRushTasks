package com.javarush.task.task31.task3107;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) throws IOException {
        Path file = Paths.get(pathToFile);
        try{
            fileData = new ConcreteFileData(Files.isHidden(file), Files.isExecutable(file), Files.isDirectory(file), Files.isWritable(file));
        }catch(Exception e){
            fileData = new NullFileData(e);
        }

    }

    public FileData getFileData() {
        return fileData;
    }

    public static void main(String[] args) throws IOException {
        Solution solution1 = new Solution("D:\\test\\data\\secondDir\\third");
        System.out.println(solution1.getFileData().isDirectory());
        Solution solution2 = new Solution("K");
        System.out.println(solution2.getFileData().isDirectory());
    }
}
