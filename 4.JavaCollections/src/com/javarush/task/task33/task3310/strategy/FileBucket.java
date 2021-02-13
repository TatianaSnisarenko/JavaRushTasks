package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("temp-",".tmp");
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public long getFileSize()  {
        try {
            return Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        return 0l;
    }

    public void putEntry(Entry entry){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))){
            outputStream.writeObject(entry);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }

    }

    public Entry getEntry(){
        Entry entry = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path))){
            if(getFileSize() > 0){
            entry = (Entry) inputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            ExceptionHandler.log(e);
        }
        return entry;
    }

    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
