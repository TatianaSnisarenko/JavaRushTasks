package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево
*/
public class CustomTree  extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;

    public CustomTree() {
        this.root = new Entry<String>("0");
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size()
    {
        int count=0;
        LinkedList<Entry<String>> linkedList = new LinkedList<Entry<String>>();
        linkedList.add(this.root);
        Entry<String> curEntry;
        while (linkedList.size()>0){
            curEntry = linkedList.getFirst();
            if (curEntry.leftChild != null){
                linkedList.add(curEntry.leftChild);
                count++;
            }
            if (curEntry.rightChild !=null){
                linkedList.add(curEntry.rightChild);
                count++;
            }
            linkedList.remove(curEntry);
        }
        return count;
    }

    @Override
    public boolean add(String s) {
        this.availableToAdd();
        boolean ret=false;
        LinkedList<Entry<String>> linkedList = new LinkedList<Entry<String>>();
        linkedList.add(this.root);
        Entry<String> curEntry = linkedList.getFirst();
        while (linkedList.size()>0 && !curEntry.isAvailableToAddChildren()){
            if (curEntry.leftChild != null){
                linkedList.add(curEntry.leftChild);
            }
            if (curEntry.rightChild != null){
                linkedList.add(curEntry.rightChild);
            }
            linkedList.remove(curEntry);
            curEntry = linkedList.getFirst();
        }
        if (curEntry.isAvailableToAddChildren()){
            if (curEntry.availableToAddLeftChildren){
                curEntry.leftChild = new Entry<String>(s);
                curEntry.leftChild.parent=curEntry;
                curEntry.availableToAddLeftChildren = false;
                ret=true;
            }
            else{
                curEntry.rightChild = new Entry<String>(s);
                curEntry.rightChild.parent=curEntry;
                curEntry.availableToAddRightChildren = false;
                ret=true;
            }
        }

        return ret;
    }
    private void availableToAdd(){
        boolean available=false;
        LinkedList<Entry<String>> linkedList = new LinkedList<Entry<String>>();
        linkedList.add(this.root);
        Entry<String> curEntry = null;
        while (linkedList.size()>0){
            curEntry = linkedList.getFirst();
            if (curEntry.isAvailableToAddChildren()){
                available = true;
                break;
            }
            if (curEntry.leftChild != null){
                linkedList.add(curEntry.leftChild);
            }
            if (curEntry.rightChild != null){
                linkedList.add(curEntry.rightChild);
            }
            linkedList.remove(curEntry);
        }

        if (!available){
            linkedList.add(this.root);
            while (linkedList.size()>0){
                curEntry = linkedList.getFirst();
                if (!curEntry.isAvailableToAddChildren()){
                    if (curEntry.leftChild == null){
                        curEntry.availableToAddLeftChildren=true;
                    }else{
                        linkedList.add(curEntry.leftChild);
                    }
                    if (curEntry.rightChild == null){
                        curEntry.availableToAddRightChildren=true;
                    }else{
                        linkedList.add(curEntry.rightChild);
                    }
                }
                linkedList.remove(curEntry);
            }
        }
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    public String getParent(String s) {
        String parentName="null";
        LinkedList<Entry<String>> linkedList = new LinkedList<Entry<String>>();
        linkedList.add(this.root);
        Entry<String> curEntry=null;
        while (linkedList.size()>0){
            curEntry = linkedList.getFirst();
            if (curEntry.elementName.equals(s)){
                break;
            }
            if (curEntry.leftChild != null){
                linkedList.add(curEntry.leftChild);
            }
            if (curEntry.rightChild != null){
                linkedList.add(curEntry.rightChild);
            }
            linkedList.remove(curEntry);
        }
        if(curEntry != null  && curEntry.elementName.equals(s) && curEntry.parent != null){
            parentName=curEntry.parent.elementName;
        }
        return parentName;
    }

    @Override
    public boolean remove(Object o) {
        Boolean ret=false;
        String name="";
        if ( !(o instanceof String)) {
            throw new UnsupportedOperationException();
        }else{
            name=(String) o;
        }

        LinkedList<Entry<String>> linkedList = new LinkedList<Entry<String>>();
        linkedList.add(this.root);
        Entry<String> curEntry=null;
        while (linkedList.size()>0){
            curEntry = linkedList.getFirst();
            if (curEntry.elementName.equals(name)){
                break;
            }
            if (curEntry.leftChild != null){
                linkedList.add(curEntry.leftChild);
            }
            if (curEntry.rightChild != null){
                linkedList.add(curEntry.rightChild);
            }
            linkedList.remove(curEntry);
        }
        if(curEntry!=null  && curEntry.elementName.equals(name) && curEntry.parent!=null){
            Entry<String> parent = curEntry.parent;
            if (parent.leftChild != null && parent.leftChild.elementName.equals(curEntry.elementName)){
                parent.leftChild=null;
                ret=true;
            }
            if (parent.rightChild != null && parent.rightChild.elementName.equals(curEntry.elementName)){
                parent.rightChild=null;
                ret=true;
            }
            if (parent.parent==this.root){
                if (parent.leftChild==null && parent.rightChild==null) {
                    parent.availableToAddLeftChildren = true;
                    parent.availableToAddRightChildren = true;
                }
            }
        }

        return ret;
    }

    static class  Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren=true;
            this.availableToAddRightChildren=true;
        }

        boolean isAvailableToAddChildren(){
            return (this.availableToAddLeftChildren || this.availableToAddRightChildren);
        }
    }
}

