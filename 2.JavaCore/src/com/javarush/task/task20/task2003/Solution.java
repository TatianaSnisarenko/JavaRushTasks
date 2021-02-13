package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.*;

/* 
Знакомство с properties
В методе fillInPropertiesMap считайте имя файла с консоли и заполни карту properties данными из файла.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties
Реализуй логику записи в файл и чтения из файла для карты properties.
*/
public class Solution {

    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        InputStream fis = new FileInputStream(fileName);
        load(fis);

        fis.close();

    }

    public void save(OutputStream outputStream) throws Exception {
        Properties pr=new Properties();
        if (!properties.isEmpty()){
            pr.putAll(properties);
            pr.store(outputStream,null);
            //implement this method - реализуйте этот метод
        }
    }

    public void load(InputStream inputStream) throws Exception {

        Properties myProperties = new Properties();
        myProperties.load(inputStream);
        myProperties.forEach( (k, v) -> properties.put( (String)k, (String)v) );

    }

    public static void main(String[] args) {

    }
}
