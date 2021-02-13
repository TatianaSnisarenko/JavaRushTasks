package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//List to Map
//Реализуй логику метода convert в классе ConvertableUtil, который должен возвращать словарь, значениями которого являются элементы переданного cписка, а ключами являются объекты, полученные вызовом интерфейсного метода getKey.
//
//Элементы cписка должны наследоваться от Convertable, который в свою очередь параметризирован каким-то ключом.
//Например, ConvertableBook параметризирован String, т.е. ключ в результирующем словаре должен иметь тип - String
//ConvertableUser параметризирован Integer, т.е. ключ в результирующем словаре должен иметь тип - Integer
//
//Значения в словаре должны совпадать с элементами Списка.
//Смотрите метод main для примера.
//
//Расставь в методе ConvertableUtil.convert дженерик типы.

public class ConvertableUtil {


    public static <K, V extends Convertable<K>>Map<K,V> convert(List<V> list) {
        Map<K,V> result = new HashMap();
        for (V value: list){
            result.put(value.getKey(), value);
        }
        return result;
    }
}
