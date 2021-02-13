package com.javarush.task.task36.task3605;

import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
public class Program {

    public static void main(String[] args) {

        try(FileReader reader = new FileReader("notes3.txt"))
        {
           // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<String> chars = new TreeSet<>();
        String result = "";
        try(FileReader fileReader = new FileReader(args[0])){
            int c;
            while((c=fileReader.read()) != -1){
                char ch = (char) c;
                if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
                    chars.add(String.valueOf(ch).toLowerCase());
                }
            }
            if(chars.size() < 5){
                for (String s:
                     chars) {
                    result +=s;
                }
            }else{
                int count = 0;
                for (String s:
                     chars) {

                    result += s;
                    count++;
                    if(count == 5) break;
                }
            }
        }
        System.out.println(result);


    }
}
