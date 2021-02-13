package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
Требования:
1. Хешкоды одинаковых объектов должны быть равны.
2. Метод equals должен проверять равен ли переданный объект текущему(сравнение через ==).
3. Метод equals должен проверять является ли переданный объект объектом класса Solution.
4. Метод equals должен возвращать true в случае, если поля first и last равны у переданного объекта и текущего(не забудь что они могут быть равны null).
5. Должно быть обеспечено корректное поведение HashSet с типом элементов Solution.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if(n == this) return true;
        if(n == null || this.getClass() != n.getClass()) return false;
        if(n instanceof Solution) {
            Solution s = (Solution) n;
            if ((this.first == s.first || this.first != null &&  this.first.equals(s.first)) && (this.last == s.last || this.last != null && this.last.equals(s.last))) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }

    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (first == null ? 0 : first.hashCode());
        result = 31 * result + (first == null ? 0 : last.hashCode());

        return result;
    }

    /*public int hashCode() {
        return 31 * first.hashCode() + last.hashCode();
    }*/

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return first.equals(solution.first) &&
                last.equals(solution.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }*/

   /* @Override
    public boolean equals(Object obj){
        if(obj == this) { return true;}
        if(obj == null || obj.getClass() != this.getClass()){return false;}
        Solution s = (Solution) obj;
        return (first.equals(s.first) && last.equals(s.last));

    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 37 * result + (first == null ? 0:first.hashCode());
        result = 37 * result + (first == null ? 0:last.hashCode());

        return result;
    }*/


    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
