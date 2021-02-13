package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.awt.print.PrinterGraphics;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        String input = outputStream.toString();
        String[] strings = input.split(System.lineSeparator());
        String newInput = "JavaRush - курсы Java онлайн";
        ArrayList<String> result = new ArrayList<>();
        for(String s: strings){
            result.add(s);
        }
        int i = 2;
        while (i < result.size()){
            result.add(i, newInput);
            i = i + 3;

        }


        System.setOut(consoleStream);
        for(String r: result){
            System.out.println(r);
        }



    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
