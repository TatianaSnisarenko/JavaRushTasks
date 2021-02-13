package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        try(BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in)); BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(consoleReader.readLine())))){
            StringBuilder sb = new StringBuilder();
            while(fileReader.ready()){
                sb.append(fileReader.readLine());

            }

            String line = sb.toString().trim();


            String[] strings = line.split(" ");
            //System.out.println(Arrays.toString(strings));
            //System.out.println(getLine(strings).toString());

            StringBuilder result = getLine(strings);
            System.out.println(result.toString());

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public static StringBuilder getLine(String... words) {

        StringBuilder result = new StringBuilder();
        if (words == null || words.length == 0) return result;
        if (words.length==1 || words[0].equals("")) return result.append(words[0]);

        ArrayList<String> wordsList = new ArrayList<>();

        wordsList.addAll(Arrays.asList(words));
        while (wordsList.remove("")){
            wordsList.remove("");
        }
        while (isYes(wordsList)) {
            Collections.shuffle(wordsList);
        }
        for (String word: wordsList){
            result.append(word).append(" ");
        }
        result.deleteCharAt(result.length()-1);
        return result;
    }

    public static boolean isYes(ArrayList<String> wordsList) {
        for (int i = 0; i < wordsList.size() - 1; i++) {
            String firstWord = wordsList.get(i).toLowerCase();
            String secondWord = wordsList.get(i + 1).toLowerCase();
            if (firstWord.charAt(firstWord.length() - 1) != secondWord.charAt(0)) return true;
        }
        return false;
    }
}