package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i = i + 3) {

                        Person person;
                        Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i + 2]);

                        if ("м".equals(args[i + 1])) {
                            person = Person.createMale(args[i], bd);
                            allPeople.add(person);
                        } else {
                            person = Person.createFemale(args[i], bd);
                            allPeople.add(person);
                        }
                        System.out.println(allPeople.size() - 1);
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i = i + 4) {


                        Person person = allPeople.get(Integer.parseInt(args[i]));
                        person.setName(args[i + 1]);
                        if ("м".equals(args[i + 2]))
                            person.setSex(Sex.MALE);
                        else person.setSex(Sex.FEMALE);

                        Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i + 3]);
                        person.setBirthDate(bd);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {


                        Person person = allPeople.get(Integer.parseInt(args[i]));


                        SimpleDateFormat bd = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                        String mbd = bd.format(person.getBirthDate());
                        String sex = "";

                        if (Sex.MALE.equals(person.getSex())) {
                            sex = "м";
                        } else {
                            sex = "ж";
                        }

                        System.out.println(person.getName() + " " + sex + " " + mbd);
                    }


                }
                break;
            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        Person person = allPeople.get(Integer.parseInt(args[i]));
                        person.setName(null);
                        person.setBirthDate(null);
                        person.setSex(null);
                    }
                }

                break;
        }
    }


}