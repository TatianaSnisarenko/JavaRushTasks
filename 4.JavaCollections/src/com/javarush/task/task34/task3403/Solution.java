package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/

public class Solution {
    public void recurse(int n) {
        int ostatok = -1;
        if (n == 1) ;
        else {
            int delitel = 2;
            while (ostatok != 0){
                ostatok = n % delitel;
                delitel++;
            }
            delitel--;
            System.out.print(delitel  + " ");
            recurse(n / delitel);
        }


    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse(87);
    }
}
