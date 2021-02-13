package com.javarush.task.task21.task2108;

import java.util.Arrays;

/*
Клонирование растений
1. Класс Plant не должен поддерживать интерфейс Cloneable.
2. Класс Tree должен поддерживать интерфейс Cloneable.
3. Класс Tree должен быть потомком класса Plant.
4. В классе Tree должен быть корректно реализован метод clone.
*/
public class Solution {
    public static void main(String[] args) {
        Tree tree = new Tree("willow", new String[]{"s1", "s2", "s3", "s4"});
        Tree clone = null;
        try {
            clone = tree.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(tree);
        System.out.println(clone);

        System.out.println(tree.branches);
        System.out.println(clone.branches);
    }

    public static class Plant{
        private String name;

        public Plant(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Tree extends Plant implements Cloneable{
        private String[] branches;

        public Tree(String name, String[] branches) {
            super(name);
            this.branches = branches;
        }

        public void setBranches(String[] branches) {
            this.branches = branches;
        }

        @Override
        protected Tree clone() throws CloneNotSupportedException {
            String [] branches = new String[this.branches.length];
            Tree tree = new Tree(super.getName(),branches);
            tree.branches = this.branches.clone();
            return tree;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tree)) return false;
            Tree tree = (Tree) o;
            return Arrays.equals(getBranches(), tree.getBranches());
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(getBranches());
        }

        public String[] getBranches() {
            return branches;
        }
    }
}
