package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        String path = args[0];
        File directory = new File(path);
        String absoluteFileName = args[1];
        File incomingFile = new File(absoluteFileName);
        String parent = incomingFile.getParent();
        String newFileAbsoluteName = parent + "/allFilesContent.txt";
        File result = new File(newFileAbsoluteName);
        if(FileUtils.isExist(incomingFile))
        FileUtils.renameFile(incomingFile, result);

        ArrayList<File> files = new ArrayList<>();

        Queue<File> queue = new PriorityQueue<>();

        Collections.addAll(queue, directory.listFiles());
        while(!queue.isEmpty()){
            File currentFile = queue.remove();
            if(currentFile.isDirectory()){
                Collections.addAll(queue, currentFile.listFiles());
            }else{
                if(currentFile.length() <= 50)
                    files.add(currentFile);
            }
        }

        Comparator<File> comparator = new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                String name1 = o1.getName();
                String name2 = o2.getName();
                return name1.compareToIgnoreCase(name2);
            }
        };

        Collections.sort(files, comparator);
        String devider = "\n";
        byte[] deviderBytes = devider.getBytes();

        try (FileOutputStream fileOutputStream = new FileOutputStream(result, true)){
            for (File file: files) {
                FileInputStream fileInputStream = new FileInputStream(file);
                //while (fis.available()>0){
                //                    fos.write(fis.read());
                //                }
                //                fos.write("\n".getBytes());
                //                fis.close();
                //            }
                //            fos.flush();
                while(fileInputStream.available() > 0)
                    fileOutputStream.write(fileInputStream.read());
                //byte[] buffer = fileInputStream.readAllBytes();
                fileInputStream.close();
                //fileOutputStream.write(buffer, 0, buffer.length);
                fileOutputStream.write(deviderBytes, 0, deviderBytes.length);

                //


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }








    }
}
