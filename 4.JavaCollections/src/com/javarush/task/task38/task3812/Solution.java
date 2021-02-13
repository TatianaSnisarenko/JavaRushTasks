package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        if(!c.isAnnotationPresent(PrepareMyTest.class)) return false;
        PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
        String[] fullyQualifiedNames = prepareMyTest.fullyQualifiedNames();
        System.out.println(Arrays.toString(fullyQualifiedNames));

        return true;
    }

    public static boolean printValues(Class c) {
        if(!c.isAnnotationPresent(PrepareMyTest.class)) return false;
        PrepareMyTest prepareMyTest = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
        Class<?>[] values = prepareMyTest.value();
        for (Class cc:
             values) {
            System.out.println(cc.getSimpleName());
        }

        return true;
    }
}
