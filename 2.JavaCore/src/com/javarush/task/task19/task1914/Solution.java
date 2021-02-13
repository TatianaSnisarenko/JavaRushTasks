package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        testString.printSomething();
        String input = outputStream.toString();
        System.setOut(consoleStream);
        String [] strs = input.split("\\s");

        int result = 0;
        if(strs[1].equals("+")){
            result = Integer.parseInt(strs[0]) + Integer.parseInt(strs[2]);
        }else if(strs[1].equals("-")){
            result = Integer.parseInt(strs[0]) - Integer.parseInt(strs[2]);
        }else if(strs[1].equals("*")){
            result = Integer.parseInt(strs[0]) * Integer.parseInt(strs[2]);
        }

        String output = input.replace(System.lineSeparator(), String.valueOf(result));
        System.out.println(output);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

