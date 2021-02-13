package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    public static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return consoleReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> clientOrder = new ArrayList<>(); 
        writeMessage("Выберете блюда из меню, пожалуйста, для выхода введите exit:");
        writeMessage(Dish.allDishesToString());
        while(true){
            String input = readString();
            if(input.equalsIgnoreCase("exit")){
                return clientOrder;
            }else{
                if(input.equalsIgnoreCase("fish")) clientOrder.add(Dish.Fish);
                else if(input.equalsIgnoreCase("steak")) clientOrder.add(Dish.Steak);
                else if(input.equalsIgnoreCase("soup")) clientOrder.add(Dish.Soup);
                else if(input.equalsIgnoreCase("juice")) clientOrder.add(Dish.Juice);
                else if(input.equalsIgnoreCase("water")) clientOrder.add(Dish.Water);
                else writeMessage("Такого блюда нет в меню");
            }
        }
    }

}
