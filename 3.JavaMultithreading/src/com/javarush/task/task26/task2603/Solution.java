package com.javarush.task.task26.task2603;

import java.util.Collections;
import java.util.Comparator;

/*
Убежденному убеждать других не трудно
В таблице есть колонки, по которым можно сортировать.
Пользователь имеет возможность настроить под себя список колонок, которые будут сортироваться.
Напиши public static компаратор CustomizedComparator, который будет:
1. В конструкторе принимать массив компараторов.
2. Сортировать данные в порядке, соответствующем последовательности компараторов.
Все переданные компараторы сортируют дженерик тип Т.
В конструктор передается как минимум один компаратор.
*/
public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T>{
        private Comparator<T>[] comparators;
        CustomizedComparator(Comparator<T>...vararg){
            this.comparators = vararg;
        }

        @Override
        public int compare(T t1, T t2) {
            int result =0;
            for (Comparator<T> comparator : comparators) {
                result = comparator.compare(t1, t2);
                if(result != 0)
                    return result;
            }
            return result;
        }
    }
    public static void main(String[] args) {
    }
}
