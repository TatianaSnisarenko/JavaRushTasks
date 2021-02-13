package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {

    private static final Object PRESENT = new Object();

    private transient HashMap<E, Object> map;



    public AmigoSet() {
        map = new HashMap<>();

    }

    public AmigoSet(Collection<? extends E > collection){
        map = new HashMap<>((int) Math.max(16, Math.ceil(collection.size() / .75f)));
        this.addAll(collection);
        }

    @Override
    public boolean add(E e) {

        return null== map.put(e, PRESENT);
    }

    @Override
    public Iterator<E> iterator() {

        Set<E> set = map.keySet();
        Iterator<E> iterator = set.iterator();
        return iterator;
    }

    @Override
    public int size() {
        return map.size();
    }


    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o)==PRESENT;
    }

    @Override
    public void clear() {
       map.clear();
    }

    @Override
    public Object clone() {
        try {
            AmigoSet copy = (AmigoSet)super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        }
        catch (Exception e) {
            throw new InternalError();
        }
    }


    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        out.writeInt(map.keySet().size());
        for(E e: map.keySet()){
            out.writeObject(e);
        }
    }


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        map = new HashMap<>(capacity, loadFactor);
        int size = in.readInt();
        for(int i = 0; i < size; i++){
            map.put((E) in.readObject(), PRESENT);
        }
    }

}
