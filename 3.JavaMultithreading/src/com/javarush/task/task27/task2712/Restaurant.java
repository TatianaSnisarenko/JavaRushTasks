package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;


public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {


        Locale.setDefault(Locale.ENGLISH);

        Waiter waiter = new Waiter();

        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(orderQueue);
        cook1.addObserver(waiter);
        Thread cooker1 = new Thread(cook1);
        cooker1.setDaemon(true);

        Cook cook2 = new Cook("Bilaabo");
        cook2.setQueue(orderQueue);
        cook2.addObserver(waiter);
        Thread cooker2 = new Thread(cook2);
        cooker2.setDaemon(true);
        cooker1.start();
        cooker2.start();


        /*List<Cook> cookList = new LinkedList<>();
        for (int i = 1; i < 3; i++) {
            Cook cook = new Cook("Повар" + i);
            cook.setQueue(orderQueue);
            //StatisticManager.getInstance().register(cook);
            cook.addObserver(waiter);
            cookList.add(cook);
            Thread thread = new Thread(cook);
            thread.setDaemon(true);
            thread.start();
        }*/



        List<Tablet> tabletList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tabletList.add(tablet);

        }

        DirectorTablet directorTablet = new DirectorTablet();

        Thread thread = new Thread(new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        thread.interrupt();
        /*cooker1.interrupt();
        cooker2.interrupt();*/

        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();

    }


}
