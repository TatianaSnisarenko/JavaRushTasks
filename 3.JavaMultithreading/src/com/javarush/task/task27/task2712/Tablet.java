package com.javarush.task.task27.task2712;
import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;


import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet{
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder(){
        Order order = null;
        try {
            order = new Order(this);
            if (orderCreatorHelper(order)) return null;

        } catch (NoVideoAvailableException e){
            logger.log(Level.INFO, "No video is available for the order " + order.toString());
        }catch (IOException e) {
            logger.severe("Console is unavailable.");
        }
        return order;

    }

    private boolean orderCreatorHelper(Order order) {
        if (order.isEmpty()) return true;
        else {
            ConsoleHelper.writeMessage(order.toString());
            //setChanged();
            AdvertisementManager adManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
            adManager.processVideos();
            queue.offer(order);

        }
        return false;
    }

    public void createTestOrder(){
        TestOrder order = null;
        try{
            order = new TestOrder(this);
            if (orderCreatorHelper(order));
        } catch (NoVideoAvailableException e){
            logger.log(Level.INFO, "No video is available for the order " + order.toString());
        }catch (IOException e) {
            logger.severe("Console is unavailable.");
        }


    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
