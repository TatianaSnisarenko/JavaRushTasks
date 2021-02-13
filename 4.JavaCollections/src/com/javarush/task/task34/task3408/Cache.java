package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here
    private K key;

    public Cache() {
    }

    public Cache(K key) {
        this.key = key;
    }

    public K getKey() {
        return key;
    }

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V result = cache.get(key);
        if(result == null){

            Class[] params = {key.getClass()};
            result = (V) clazz.getConstructor(params).newInstance(key);
            cache.put(key, result);
        }
        //TODO add your code here
        return result;
    }

    public boolean put(V obj) {

        try {
            Method getKeyMethod = obj.getClass().getDeclaredMethod("getKey");
            getKeyMethod.setAccessible(true);
            K key = (K) getKeyMethod.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return false;
            //TODO add your code here

        }
    }

    public int size() {
        return cache.size();
    }
}
