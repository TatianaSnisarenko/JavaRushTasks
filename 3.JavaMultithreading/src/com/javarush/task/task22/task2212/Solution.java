package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
Критерии валидности:
1) если номер начинается с '+', то он содержит 12 цифр
2) если номер начинается с цифры или открывающей скобки, то он содержит 10 цифр
3) может содержать 0-2 знаков '-', которые не могут идти подряд
4) может содержать 1 пару скобок '(' и ')' , причем если она есть, то она расположена левее знаков '-'
5) скобки внутри содержат четко 3 цифры
6) номер не содержит букв
7) номер заканчивается на цифру
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if(telNumber == null || telNumber.isEmpty()){
            return false;
        }
        String temp = telNumber;
        int length = temp.replaceAll("\\D", "").length();
        if (telNumber.contains("[a-aA-Z]")) {return false;}
        if (length==12) {
            return telNumber.matches("(^\\+{1})\\d*(\\(\\d{3}\\))?\\d*(\\-?\\d+)?\\-?\\d*\\d$");
        }
        else if (length==10) {
            return telNumber.matches("^(\\d|\\(\\d{3}\\))\\d*(\\-?\\d+)?\\-?\\d*\\d$");
        }
        return false;


    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567"));
        System.out.println(checkTelNumber("+38(050)1234567"));
        System.out.println(checkTelNumber("+38050123-45-67"));
        System.out.println(checkTelNumber("050123-4567"));
        System.out.println(checkTelNumber("+38)050(1234567"));
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));
        System.out.println(checkTelNumber("050ххх4567"));
        System.out.println(checkTelNumber("050123456"));
        System.out.println(checkTelNumber("(0)501234567"));
        System.out.println(checkTelNumber("+38(050)123-45-67"));
        System.out.println(checkTelNumber("(050)1234567"));
        System.out.println(checkTelNumber("(050)123-45-67"));

    }
}
