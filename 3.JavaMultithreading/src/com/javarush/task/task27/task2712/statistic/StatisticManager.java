package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();
    //private Set<Cook> cooks = new HashSet<>();

   /* public Set<Cook> getCooks() {
        return cooks;
    }

    public void register(Cook cook){
        cooks.add(cook);
    }
*/
    //



    /*public Map<Date, Map<String,Integer>> getDataAboutCooker(){
        List<EventDataRow> eventDataAboutCookedOrder = statisticStorage.getStorage(EventType.COOKED_ORDER);
        Map<Date, Map<String,Integer>> totalWorkForDay = new TreeMap<>(Collections.reverseOrder());

        for(EventDataRow eventDataRow : eventDataAboutCookedOrder){
            CookedOrderEventDataRow cookedOrderEventDataRow = (CookedOrderEventDataRow) eventDataRow;
            Date date = cookedOrderEventDataRow.getDate();
            String cookerName = cookedOrderEventDataRow.getCookName();
            int workingTime = cookedOrderEventDataRow.getTime();
            Map<String, Integer> currentInnerMap = totalWorkForDay.get(date);
            if(currentInnerMap == null) {
                currentInnerMap = new TreeMap<>();
                currentInnerMap.put(cookerName, workingTime);
            }
            else {
                int previousWorkingTime = currentInnerMap.get(cookerName) == null ? 0:currentInnerMap.get(cookerName);
                int newWorkingTime = workingTime + previousWorkingTime;
                currentInnerMap.put(cookerName, newWorkingTime);
            }
            totalWorkForDay.put(date, currentInnerMap);
       }
        return totalWorkForDay;

    }*/

    public Map<String, Map<String, Integer>> povar() {
        Map<String, Map<String, Integer>> result = new TreeMap<>(Collections.reverseOrder());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (EventDataRow event : statisticStorage.getStorage(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow cookRow = (CookedOrderEventDataRow) event;
            String data = sdf.format(cookRow.getDate());
            String name = cookRow.getCookName();
            Integer time = (int) Math.ceil(cookRow.getTime() / 60.00);
            if (time > 0) {
                if (!result.containsKey(data)) {
                    Map<String, Integer> inner = new TreeMap<>();
                    inner.put(name, time);
                    result.put(data, inner);
                } else {
                    Map<String, Integer> inner = result.get(data);
                    if (!inner.containsKey(name)) {
                        inner.put(name, time);
                    } else {
                        inner.put(name, inner.get(name) + time);
                    }
                }
            }
        }
        return result;
    }

    public Map<String, Double> reklama() {
        Map<String, Double> result = new TreeMap<>(Collections.reverseOrder());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (EventDataRow video : statisticStorage.getStorage(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow videoRow = (VideoSelectedEventDataRow) video;
            double allMoney = videoRow.getAmount();
            String data = sdf.format(videoRow.getDate());
            if (result.containsKey(data)) {
                result.put(data, result.get(data) + allMoney / 100.00);
            }
            else {
                result.put(data, allMoney / 100.00);
            }
        }
        return result;
    }

    /*public Map<Date, Double> getDataAboutAdvetisement(){
        List<EventDataRow> eventDataAboutSelectedVideos = statisticStorage.getDataFromEvent(EventType.SELECTED_VIDEOS);
        Map<Date, Double> totalAmountForDay = new TreeMap<>(Collections.reverseOrder());
        for(EventDataRow eventDataRow : eventDataAboutSelectedVideos){
            VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            double amount = videoSelectedEventDataRow.getAmount();
            Date date = videoSelectedEventDataRow.getDate();
            double previousAmount = totalAmountForDay.get(date) == null ? 0:totalAmountForDay.get(date);
            double newAmount = (previousAmount + amount) / 100.00;
            totalAmountForDay.put(date, newAmount);
        }
        return totalAmountForDay;

    }*/

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        if(instance == null) instance = new StatisticManager();
        return instance;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage;

        public StatisticStorage() {
            storage = new HashMap<>();
            for (EventType type:EventType.values()
                 ) {
                storage.put(type, new ArrayList<>());
            }

        }

        /*public List<EventDataRow> getDataFromEvent(EventType eventType){
            return storage.get(eventType);
        }*/

        private List<EventDataRow> getStorage(EventType type) {
            return storage.get(type);
        }

        private void put(EventDataRow data){
           storage.get(data.getType()).add(data);
        }
    }

}
