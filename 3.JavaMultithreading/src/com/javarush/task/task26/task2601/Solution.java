package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
       /* Integer[] array = {13, 8, 15, 5, 17, 12, 14, 16, 20, 100, 300, 290, 1000, 1011};
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println(Arrays.toString(array));*/
    }

    public static Integer[] sort(Integer[] array) {
        Integer []copyArray = Arrays.copyOf(array, array.length);
        Arrays.sort(copyArray);
        int median;

        if(copyArray.length % 2 == 0){
            median = (copyArray[(copyArray.length / 2 - 1)] + copyArray[(copyArray.length / 2)]) / 2;

        }else{
            median = copyArray[copyArray.length / 2];
        }

        Comparator<Integer> compareByMeridian = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return Math.abs(median - i1) - Math.abs(median - i2);
            }
        };
        Comparator<Integer> compareByValue = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2);
            }
        };
        //Arrays.sort(array, Comparator.comparingInt((Integer o) -> Math.abs(mediana - o)).thenComparingInt(o -> o));
        Arrays.sort(array,compareByMeridian.thenComparing(compareByValue));
        return array;
    }
}
