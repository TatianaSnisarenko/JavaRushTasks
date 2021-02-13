package com.javarush.task.task38.task3803;

public class VeryComplexClass {
    public void methodThrowsClassCastException (){
        Object object = new Integer(2);
        String s = (String) object;

    }

    public void methodThrowsNullPointerException(){
        String s = null;
        s.getBytes();

    }

    public static void main(String[] args) {
        VeryComplexClass veryComplexClass = new VeryComplexClass();
        veryComplexClass.methodThrowsNullPointerException();

    }
}
