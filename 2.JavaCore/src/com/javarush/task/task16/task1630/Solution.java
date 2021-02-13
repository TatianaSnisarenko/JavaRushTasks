package com.javarush.task.task16.task1630;

/*      Последовательный вывод файлов
        1. Разберись, что делает программа.
        2. В статическом блоке считай 2 имени файла firstFileName и secondFileName.
        3. Внутри класса Solution создай нить public static ReadFileThread, которая реализует
        интерфейс ReadFileInterface (Подумай, что больше подходит - Thread или Runnable).
        3.1. Метод setFileName должен устанавливать имя файла, из которого будет читаться содержимое.
        3.2. Метод getFileContent должен возвращать содержимое файла.
        3.3. В методе run считай содержимое файла, закрой поток. Раздели пробелом строки файла.
        4. Подумай, в каком месте нужно подождать окончания работы нити, чтобы обеспечить последовательный вывод файлов.
        4.1. Для этого добавь вызов соответствующего метода.*/

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
            reader.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        //add your code here - добавьте код тут
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface{
        private String fileName;
        private String fileContent = "";

        @Override
        public void setFileName(String fullFileName) {
            fileName = fullFileName;
        }

        @Override
        public String getFileContent() {
            return fileContent;
        }

        public void run(){

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));

                while(reader.ready()){
                    fileContent = fileContent + reader.readLine()+ " " ;
                }
                reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }
}
