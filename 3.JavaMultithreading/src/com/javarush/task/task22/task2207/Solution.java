package com.javarush.task.task22.task2207;

import javax.imageio.stream.FileImageInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {

            try(BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in)); BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(consoleReader.readLine())))){
                StringBuilder sb = new StringBuilder();
                while (fileReader.ready()){
                    sb.append(fileReader.readLine().replaceAll("\uFEFF", ""));
                    sb.append(" ");

                }
                String line = sb.toString().trim();


                String[] strings = line.split("(\\s|\\r|\\n)+");



                for (int i = 0; i < strings.length - 2; i++) {
                    for (int j = i + 1; j < strings.length - 1; j++) {


                        StringBuilder sbTempo = new StringBuilder(strings[j]);
                        String reverseString = sbTempo.reverse().toString();

                        if(strings[i].equals(reverseString)){
                            Pair e = new Pair();
                            e.setFirst(strings[i]);
                            e.setSecond(strings[j]);
                            if(!result.contains(e)) {

                                result.add(e);
                            }
                        }

                    }

                }

            } catch (IOException e) {
            e.printStackTrace();
        }
            for(Pair pair: result){
                System.out.println(pair.toString());
            }


    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
