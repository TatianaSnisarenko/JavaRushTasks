package com.javarush.task.task20.task2024;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
1. В классе Solution должно существовать поле edges.
2. В классе Solution должно существовать поле node.
3. Тип поля node должен быть int.
4. Класс Solution должен поддерживать интерфейс Serializable.
*/
public class Solution implements Serializable {
    int node;
    List<Solution> edges = new LinkedList<>();

    public static void main(String[] args) {

    }
}
