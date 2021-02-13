package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        switch (args[0]) {
            case "-c":
                create(args);
                break;
            case "-u":
                update(args);
                break;
            case "-i":
                read(args);
                break;
            case "-d":
                delete(args);
                break;
        }
    }

    public static void create(String[] args) throws ParseException {
        Person person;
        Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);

        if ("м".equals(args[2])) {
            person = Person.createMale(args[1], bd);
            allPeople.add(person);
        } else {
            person = Person.createFemale(args[1], bd);
            allPeople.add(person);
        }
        System.out.println(allPeople.size() - 1);
    }

    public static void update(String[] args) throws ParseException {
        Person person = allPeople.get(Integer.parseInt(args[1]));
        person.setName(args[2]);
        if ("м".equals(args[3]))
            person.setSex(Sex.MALE);
        else person.setSex(Sex.FEMALE);

        Date bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]);
        person.setBirthDate(bd);
    }

    public static void read(String[] args) throws ParseException {
        Person person = allPeople.get(Integer.parseInt(args[1]));


        SimpleDateFormat bd = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH );
        String mbd = bd.format(person.getBirthDate());
        String sex = "";

        if (Sex.MALE.equals(person.getSex())) {
            sex = "м";
        } else {
            sex = "ж";
        }

        System.out.println(person.getName() + " " + sex + " " + mbd);


    }

    public static void delete(String[] args) {
        Person person = allPeople.get(Integer.parseInt(args[1]));
        person.setName(null);
        person.setBirthDate(null);
        person.setSex(null);
    }
}
