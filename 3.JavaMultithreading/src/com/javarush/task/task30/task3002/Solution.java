package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {
        int radix;
        String str = "";
        if(s.substring(0,1).equals("0")){
            if(s.substring(1,2).equals("x")){
                radix = 16;
                str = s.substring(2,s.length());
            }else if(s.substring(1,2).equals("b")){
                radix = 2;
                str = s.substring(2,s.length());
            }else{
                radix = 8;
                str = s.substring(1,s.length());
            }
        }else{
            radix = 10;
            str = s;
        }
        //напишите тут ваш код
        Integer a = Integer.parseInt(str, radix);
        return a.toString();
    }
}
