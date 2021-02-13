package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> ids = new HashSet<>();
        for(String s: strings){
            ids.add(shortener.getId(s));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> strings = new HashSet<>();
        for( Long key : keys){
            strings.add(shortener.getString(key));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> generatedStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            generatedStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Date currentDate = new Date();
        Set<Long> keys = getIds(shortener, generatedStrings);
        Date nextDate = new Date();
        long miliseconds = nextDate.getTime() - currentDate.getTime();
        Helper.printMessage("Amount of milliseconds for method getIds " + miliseconds);
        currentDate = new Date();
        Set<String> strings = getStrings(shortener, keys);
        nextDate = new Date();
        miliseconds = nextDate.getTime() - currentDate.getTime();
        Helper.printMessage("Amount of milliseconds for method getStrings " + miliseconds);
        if(generatedStrings.equals(strings)){
            Helper.printMessage("Тест пройден.");
        }else{
            Helper.printMessage("Тест не пройден.");
        }

    }

    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        System.out.println();
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        System.out.println();
        testStrategy(new FileStorageStrategy(), 10);
        System.out.println();
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        System.out.println();
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        System.out.println();
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);


    }
}
