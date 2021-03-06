package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
Методы newArrayList, newHashSet параметризируй типом T.
Метод newHashMap параметризируй типами К(ключ) и V(значение). Аргументы метода newHashMap должны принимать списки, в которых содержатся наследники типов K и V.
Возвращаемые коллекции должны быть такого же типа, что и переданные в метод объекты.
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList<T> list = new ArrayList<>();
        for (T element: elements
             ) {
            list.add(element);
        }
        //напишите тут ваш код
        return list;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        HashSet<T> hashSet = new HashSet<>();
        for(T element: elements){
            hashSet.add(element);
        }
        return hashSet;
    }

    public static <K,V> HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        if(keys.size() == values.size()) {
            HashMap<K, V> hashMap = new HashMap<>();
            for (int i = 0; i < keys.size(); i++) {
                hashMap.put(keys.get(i), values.get(i));
            }
            return hashMap;
        }else{
            throw new IllegalArgumentException();
        }


    }
}
