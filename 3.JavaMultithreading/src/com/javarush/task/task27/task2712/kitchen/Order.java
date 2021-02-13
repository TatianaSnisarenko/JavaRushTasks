package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Tablet getTablet() {
        return tablet;
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        if(dishes == null || dishes.size() == 0) return "";
        else return "Your order: " + dishes.toString() + " of " + tablet.toString();

    }

    public int getTotalCookingTime(){
        int cookingTime = 0;
        for(Dish dish: dishes){
            cookingTime += dish.getDuration();
        }
        return cookingTime;
    }

    public boolean isEmpty(){
        return dishes == null || dishes.size() == 0;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
