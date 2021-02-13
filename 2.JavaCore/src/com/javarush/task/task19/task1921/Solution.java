package com.javarush.task.task19.task1921;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            ArrayList<String> strings = new ArrayList<>();
            while(reader.ready()){
                strings.add(reader.readLine());
            }
            for(String s: strings){
                String[] line = s.split(" ");

                int day = Integer.parseInt(line[line.length - 3]);
                int month = Integer.parseInt(line[line.length - 2]);
                int year = Integer.parseInt(line[line.length - 1]);


                String date = String.format("%02d %02d %d",day,month,year);

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < line.length - 3; i++) {
                    sb.append(line[i] + " ");
                }
                String preName = sb.toString();
                String name = preName.substring(0, preName.length() - 1);
                DateFormat df = new SimpleDateFormat("dd MM yyyy");
                try {
                    Date birthDate = df.parse(date);
                    PEOPLE.add(new Person(name, birthDate));
                }catch(ParseException e){
                    e.printStackTrace();
                }
            }

        reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }



    }
}
