package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

     public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        if (!(o instanceof Solution))
            return false;
        Solution n = (Solution) o;
        return (this.first == n.first || this.first != null && n.first.equals(this.first)) && (this.last == n.last || this.last != null && n.last.equals(last));
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + (first == null ? 0 : first.hashCode());
        result = 31 * result + (first == null ? 0 : last.hashCode());

        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
