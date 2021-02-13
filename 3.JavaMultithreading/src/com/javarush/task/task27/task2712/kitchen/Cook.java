package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Objects;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;


    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }



    public void startCookingOrder(Order order){
        busy = true;
        StatisticManager statisticManager = StatisticManager.getInstance();

        statisticManager.register(new CookedOrderEventDataRow(order.getTablet().toString(), this.name, order.getTotalCookingTime()*60, order.getDishes()));
        //statisticManager.register(this);
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
            return;
        }
        setChanged();
        notifyObservers(order);
        busy = false;
    }


    @Override
    public void run() {
        StatisticManager statisticManager = StatisticManager.getInstance();

        while(true){
            if(!queue.isEmpty() && !this.isBusy()){
                Order order = queue.poll();
                if(order != null)
                this.startCookingOrder(order);;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
