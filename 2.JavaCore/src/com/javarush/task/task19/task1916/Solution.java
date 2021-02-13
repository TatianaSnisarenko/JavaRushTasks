package com.javarush.task.task19.task1916;

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        try{
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fileReader1 = new BufferedReader(new FileReader(consoleReader.readLine()));
            BufferedReader fileReader2 = new BufferedReader(new FileReader(consoleReader.readLine()));
            consoleReader.close();
            ArrayList<String> file1 = new ArrayList<>();
            ArrayList<String> file2 = new ArrayList<>();


            while(fileReader1.ready()){
                file1.add(fileReader1.readLine());
            }
            fileReader1.close();

            while(fileReader2.ready()){
                file2.add(fileReader2.readLine());
            }


            fileReader2.close();

            int index1 = 0;
            int index2 = 0;

            while(index1 < file1.size() || index2 < file2.size()){
                if(index1 < file1.size() && index2 < file2.size() && file1.get(index1).equals(file2.get(index2))){
                    lines.add(new LineItem(Type.SAME, file1.get(index1)));
                    index1++;
                    index2++;

                }else if(index1 + 1 < file1.size() && index2 < file2.size() && file1.get(index1 + 1).equals(file2.get(index2))){
                    lines.add(new LineItem(Type.REMOVED, file1.get(index1)));
                    lines.add(new LineItem(Type.SAME, file1.get(index1 + 1)));
                    index1 = index1 + 2;
                    index2 = index2 + 1;
                }else if(index2 + 1 < file2.size() && index1 < file1.size() && file1.get(index1).equals(file2.get(index2 + 1))){
                    lines.add(new LineItem(Type.ADDED, file2.get(index2)));
                    lines.add(new LineItem(Type.SAME, file1.get(index1)));
                    index1++;
                    index2 = index2 + 2;
                }else if(index1 + 1 >= file1.size() && index2 < file2.size()){
                    lines.add(new LineItem(Type.ADDED, file2.get(index2)));
                    index2++;
                }else if(index2 +1 >= file2.size() && index1 < file1.size()){
                    lines.add(new LineItem(Type.REMOVED, file1.get(index1)));
                    index1++;
                }

            }





            for(LineItem line: lines){
                System.out.println(line);
            }




        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        public String toString(){
            return type + " " + line;
        }
    }
}
