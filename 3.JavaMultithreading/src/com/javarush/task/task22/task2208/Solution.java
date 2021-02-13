package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
Пример:
{name=Ivanov, country=Ukraine, city=Kiev, age=null}

Результат:
name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'
*/
public class Solution {
    public static void main(String[] args) {
        /*HashMap<String, String> map = new HashMap<>();

        *//*map.put("name","Ivavov");
        map.put("country","Ukraine");
        map.put("city", "Kiev");
        map.put("age",null);*//*
        System.out.println(getQuery(map));*/

    }
    public static String getQuery(Map<String, String> params) {
        String result = "";
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String> pair: params.entrySet()){
            String key = pair.getKey();
            String value = pair.getValue();
            if(value != null){
                String s = key + " = '" + value + "' and ";
                sb.append(s);

            }
        }
        if(sb.length() >= 5) {
            sb.delete(sb.length() - 5, sb.length());
            result = sb.toString().trim();
        }
        return result;
    }
}
