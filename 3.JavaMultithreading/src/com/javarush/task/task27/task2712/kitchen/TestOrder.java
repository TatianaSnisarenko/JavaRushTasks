package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }


    /*@Override
    protected void initDishes(){
        dishes = new ArrayList<>();
        int randomCountOfDishes = (int)Math.random() * 5;
        for (int i = 0; i < randomCountOfDishes; i++) {
            Dish[] allDishes = Dish.values();
            dishes.add(allDishes[(int)Math.random()*i + 1]);
        }
    }*/

    protected void initDishes() {
        int a = Dish.values().length;
        dishes = new LinkedList<>();
        int numOfDishes = (int) (Math.random() * (a - 1)) + 1;
        for (int i = 0; i < numOfDishes; i++) {
            int numDish = (int) (Math.random() * (a));
            Dish dish = Dish.values()[numDish];
            dishes.add(dish);
        }
    }
}


