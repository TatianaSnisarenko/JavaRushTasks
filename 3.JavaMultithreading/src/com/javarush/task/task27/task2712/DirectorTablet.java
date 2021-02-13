package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {

   /* public void printAdvertisementProfit(){ //какую сумму заработали на рекламе, сгруппировать по дням

        Map<Date, Double> advertisementProfitForDay = StatisticManager.getInstance().getDataAboutAdvetisement();
        Locale.setDefault(Locale.ENGLISH);
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        double totalAmount = 0;
        for(Map.Entry<Date, Double> pair: advertisementProfitForDay.entrySet()){
            Date date = pair.getKey();
            Double amount = pair.getValue();
            totalAmount += amount;
            String stringAmount = String.format(Locale.ENGLISH,"%.2f", amount);
            ConsoleHelper.writeMessage(dateFormat.format(date) + " - " + stringAmount);
        }
        String stringTotalAmount = String.format(Locale.ENGLISH,"%.2f", totalAmount);
        ConsoleHelper.writeMessage("Total - " + stringTotalAmount);
    }*/

    public void printAdvertisementProfit() {
        double total = 0.0;
        Map<String, Double> profit = StatisticManager.getInstance().reklama();
        for (Map.Entry<String, Double> prof : profit.entrySet()) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", prof.getKey(), prof.getValue()));
            total += prof.getValue();
        }
        if (total > 0) {
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", total));
        }
    }

    public void printCookWorkloading() {
        Map<String, Map<String, Integer>> loading = StatisticManager.getInstance().povar();
        for (Map.Entry<String, Map<String, Integer>> load : loading.entrySet()) {
            ConsoleHelper.writeMessage(load.getKey());
            for (Map.Entry<String, Integer> inner : load.getValue().entrySet()) {
                //    int workTime = (int) Math.ceil(inner.getValue() / 60.0);
                ConsoleHelper.writeMessage(String.format("%s - %d min", inner.getKey(), inner.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
    }


   /* public void printCookWorkloading(){ //загрузка (рабочее время) повара, сгруппировать по дням
        Map<Date, Map<String,Integer>> totalCookWorkForDay = StatisticManager.getInstance().getDataAboutCooker();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for(Map.Entry<Date, Map<String, Integer>> pair: totalCookWorkForDay.entrySet()){
            Date date = pair.getKey();
            ConsoleHelper.writeMessage(dateFormat.format(date));
            Map<String, Integer> innerMap = pair.getValue();
            for(Map.Entry<String, Integer> innerPair: innerMap.entrySet()){
                String cookName = innerPair.getKey();
                Integer workingTime = innerPair.getValue();
                if(workingTime != null || workingTime != 0){
                    int workingTimeInMinutes = (int)Math.ceil(workingTime / 60.0);
                    ConsoleHelper.writeMessage(cookName + " - " + workingTimeInMinutes + " min");
                }
            }
            ConsoleHelper.writeMessage("");
        }
    }*/
    public void printActiveVideoSet(){//список активных роликов и оставшееся количество показов по каждому
        List<Advertisement> activeAds = StatisticAdvertisementManager.getInstance().getActiveAdvertisements();
        for(Advertisement ad: activeAds){
            ConsoleHelper.writeMessage(ad.getName() + " - " + ad.getHits());
        }
    }
    public void printArchivedVideoSet(){ //список неактивных роликов (с оставшемся количеством показов равным нулю)
        List<Advertisement> notActiveAds = StatisticAdvertisementManager.getInstance().getNotActiveAdvertisements();
        for(Advertisement ad: notActiveAds){
            ConsoleHelper.writeMessage(ad.getName());
        }
    }
}
