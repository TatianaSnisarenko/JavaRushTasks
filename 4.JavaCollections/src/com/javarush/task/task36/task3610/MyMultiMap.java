package com.javarush.task.task36.task3610;

//Задача MyMultiMap:
//Условие (в описании задачи): Если по какому-то ключу хранится лист размером ноль элементов - удали такую пару ключ : значение
//Валидатор (в описании ошибки): Если после удаления по ключу хранится лист размером ноль элементов - удали такую пару ключ : лист.
//
//Хорошо хоть валидатор подсказывает, что же в итоге он ждет... :)

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        int countOfValues = 0;
        for (Map.Entry<K, List<V>> pair : map.entrySet()) {
            List<V> values = pair.getValue();
            for (V value : values) {
                if (value != null)
                    countOfValues++;
            }
        }
        return countOfValues;
        //напишите тут ваш код
    }

    @Override
    public V put(K key, V value) {
        V lastAddedValue = null;
        if (map.containsKey(key)) {
            List<V> values = map.get(key);
            int indexOfLastAdded = values.size() - 1;
            if (indexOfLastAdded >= 0) {
                lastAddedValue = values.get(indexOfLastAdded);
            }

            if (values.size() < repeatCount) {
                values.add(value);
            } else if (values.size() == repeatCount) {
                values.remove(0);
                values.add(value);
            }
        } else {
            List<V> newValues = new ArrayList<>();
            newValues.add(value);
            map.put(key, newValues);

        }
        return lastAddedValue;
        //напишите тут ваш код
    }

    @Override
    public V remove(Object key) {
        V removedValue = null;
        if (map.containsKey(key)) {
            List<V> values = map.get(key);
            if (values.size() == 0) {
                map.remove(key);
            } else {
                removedValue = values.remove(0);
                if (values.size() == 0) {
                    map.remove(key);
                }

            }
            //напишите тут ваш код

        }
        return removedValue;
    }

        @Override
        public Set<K> keySet () {
            return map.keySet();
            //напишите тут ваш код
        }

        @Override
        public Collection<V> values () {
        ArrayList<V> allValues = new ArrayList<>();
        for(Map.Entry<K, List<V>> pair: map.entrySet()){
            List<V> values = pair.getValue();
            allValues.addAll(values);
        }
        return allValues;
            //напишите тут ваш код
        }

        @Override
        public boolean containsKey (Object key){
            return map.containsKey(key);
            //напишите тут ваш код
        }

        @Override
        public boolean containsValue (Object value){
        Collection <V> allValues = values();
            return allValues.contains(value);
            //напишите тут ваш код
        }

        @Override
        public String toString () {
            StringBuilder sb = new StringBuilder("{");
            for (Map.Entry<K, List<V>> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                for (V v : entry.getValue()) {
                    sb.append(v);
                    sb.append(", ");
                }
            }
            String substring = sb.substring(0, sb.length() - 2);
            return substring + "}";
        }
    }