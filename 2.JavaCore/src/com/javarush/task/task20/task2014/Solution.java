package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
Написать код проверки самостоятельно в методе main:
1) создать файл, открыть поток на чтение (input stream) и на запись(output stream);
2) создать экземпляр класса Solution - savedObject;
3) записать в поток на запись savedObject (убедитесь, что они там действительно есть);
4) создать другой экземпляр класса Solution с другим параметром;
5) загрузить из потока на чтение объект - loadedObject;
6) проверить, что savedObject.string равна loadedObject.string;
7) обработать исключения.


*/
public class Solution implements Serializable {
    public static void main(String[] args) {
        try {

             Solution savedObject = new Solution(5);
             FileOutputStream fileOutput = new FileOutputStream("D:\\data.txt");
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
             outputStream.writeObject(savedObject);

             Solution anotherObject = new Solution(10);

            FileInputStream fileInput = new FileInputStream("D:\\data.txt");
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            Solution loadedObject = (Solution)inputStream.readObject();

            System.out.println(loadedObject.equals(savedObject));
            System.out.println(loadedObject);
            System.out.println(savedObject);

            outputStream.close();
            inputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(new Solution(4));
    }

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(){

    }

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
