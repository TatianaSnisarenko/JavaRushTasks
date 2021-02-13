package com.javarush.task.task20.task2017;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {

        A result = null;
        try {

           Object o = objectStream.readObject();
           if(o instanceof A){
               result = (A) o;
               return result;
           }else{
               return null;
           }

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
