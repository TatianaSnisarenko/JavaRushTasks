package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        String result = null;
        int first = string.indexOf('\t');
        if(first == -1) throw new TooShortStringException();
        int last = string.indexOf("\t", first + 1);
        if(last == -1) throw new TooShortStringException();
        if(first != -1 && last != -1){
            result = string.substring(first + 1, last);
        }


        return result;
    }

    public static class TooShortStringException extends Exception {
        public TooShortStringException(){}

    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
