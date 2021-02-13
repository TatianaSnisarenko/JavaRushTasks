package com.javarush.task.task32.task3204;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 
Генератор паролей
*/
public class Solution {
    private  static List<String> passwords = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        Boolean unique = false;
        String password = "";
        while(!unique) {

            int rSmallStr = (int) (Math.random() * 6 + 1);
            for (int i = 0; i < rSmallStr; i++) {
                password = password + randomCharacter("abcdefghijklmnopqrstuvwxyz");
            }
            int rBigStr = (int) (Math.random() * (7 - password.length()) + 1);
            for (int i = 0; i < rBigStr; i++) {
                password = insertAtRandom(password, randomCharacter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
            }
            int rDigits = 8 - password.length();
            for (int i = 0; i < rDigits; i++) {
                password = insertAtRandom(password, randomCharacter("0123456789"));
            }
            if (!passwords.contains(password)) {
                unique = true;
                break;
            }else{
                password = "";
            }
        }
        passwords.add(password);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(password.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        BufferedInputStream bis = new BufferedInputStream(byteArrayInputStream);
        while(bis.available() > 0){
            int data = bis.read();
            byteArrayOutputStream.write(data);
        }



        return byteArrayOutputStream;
    }

    public static String randomCharacter(String characters) {
        int n = characters.length();
        int r = (int) (n * Math.random());
        return characters.substring(r, r + 1);
    }
    public static String insertAtRandom(String str, String toInsert){
        int n = str.length();
        int r = (int) ((n + 1) * Math.random());
        return str.substring(0,r) + toInsert + str.substring(r);
    }
}