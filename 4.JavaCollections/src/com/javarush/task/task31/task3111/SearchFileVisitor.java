package com.javarush.task.task31.task3111;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {


    private String partOfName = "";
    private String partOfContent = "";
    private int minSize = -1;
    private int maxSize = -1;
    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    private boolean checkName(Path file) {
        File currentFile = new File(file.toString());
        String fileName = currentFile.getName();
        if (fileName.contains(partOfName)) {
            return true;
        }
        return false;
    }

    private boolean checkContent(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file);
        for (String s : lines) {
            if (s.contains(partOfContent)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMaxSize(Path file) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        if (content.length < maxSize) {
            return true;

        }
        return false;
    }

    private boolean checkMinSize(Path file)throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        if (content.length > minSize) {
            return true;

        }
        return false;
    }

    private boolean checkBothSize(Path file)throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        if (content.length > minSize && content.length < maxSize) {
            return true;

        }
        return false;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        int maska = 0;

        if(!partOfName.isEmpty()) maska |= 1;
        if(!partOfContent.isEmpty()) maska |= 2;
        if(maxSize > 0) maska |= 4;
        if(minSize > 0) maska |= 8;


        if(maska == 1){
            if(checkName(file)) foundFiles.add(file);
        }else if(maska == 2){
            if(checkContent(file)) foundFiles.add(file);
        }else if(maska == 3){
            if(checkName(file) && checkContent(file)) foundFiles.add(file);
        }else if(maska == 4){
            if(checkMaxSize(file)) foundFiles.add(file);
        }else if(maska == 5){
            if(checkName(file) && checkMaxSize(file)) foundFiles.add(file);

        }else if(maska == 6){
            if(checkContent(file) && checkMaxSize(file)) foundFiles.add(file);

        }else if(maska == 7){
            if(checkName(file) && checkContent(file) && checkMaxSize(file)) foundFiles.add(file);
        }else if(maska == 8){
            if(checkMinSize(file)) foundFiles.add(file);

        }else if(maska == 9){
            if(checkMinSize(file) && checkName(file)) foundFiles.add(file);

        }else if(maska == 10){
            if(checkContent(file) && checkMinSize(file)) foundFiles.add(file);
        }else if(maska == 11){
            if(checkName(file) && checkContent(file) && checkMinSize(file)) foundFiles.add(file);

        }else if(maska ==12){
            if(checkBothSize(file)) foundFiles.add(file);

        }else if(maska == 13){
            if(checkName(file) && checkBothSize(file)) foundFiles.add(file);
        }else if(maska == 14){
            if(checkContent(file) && checkBothSize(file)) foundFiles.add(file);
        }else if(maska == 15){
            if(checkName(file) && checkContent(file) && checkBothSize(file)) foundFiles.add(file);
        }

        return FileVisitResult.CONTINUE;
    }
}
