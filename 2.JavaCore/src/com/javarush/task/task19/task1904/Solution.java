package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
Иванов Иван Иванович 31 12 1950

*/

public class Solution {

    public static void main(String[] args) {



    }

    public static class PersonScannerAdapter implements PersonScanner{
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String s = fileScanner.nextLine();
            String[] strings = s.split(" ");
            String firstName = strings[1];
            String middleName = strings[2];
            String lastName = strings[0];
            String date = strings[3] + strings[4] + strings[5];
            DateFormat format = new SimpleDateFormat("ddMMyyyy");
            Date birthDate = new Date();
            try {
                birthDate = format.parse(date);
            }catch(ParseException e){
                e.printStackTrace();
            }

            return new Person(firstName, middleName, lastName, birthDate);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();

        }
    }
}
