package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//StatisticAdvertisementManager, который будет предоставлять информацию из AdvertisementStorage в нужном нам виде.
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance;
    private AdvertisementStorage storage= AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }
    public static StatisticAdvertisementManager getInstance(){
        if(instance == null) instance = new StatisticAdvertisementManager();
        return instance;
    }
    //В StatisticAdvertisementManager создай два (или один) метода (придумать самостоятельно),
    // которые из хранилища AdvertisementStorage достанут все необходимые данные - соответственно список активных и неактивных рекламных роликов.
    //Активным роликом считается тот, у которого есть минимум один доступный показ.
    //Неактивным роликом считается тот, у которого количество показов равно 0.

    public List<Advertisement> getActiveAdvertisements(){
        List<Advertisement> activeAdvertisements = new ArrayList<>();
        List<Advertisement> allAdvertisements = storage.list();
        for(Advertisement ad: allAdvertisements){
            if(ad.getHits() > 0){
                activeAdvertisements.add(ad);
            }
        }
        Comparator<Advertisement> comparator = new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
        activeAdvertisements.sort(comparator);
        return activeAdvertisements;
    }

    public List<Advertisement> getNotActiveAdvertisements(){
        List<Advertisement> notActiveAdvertisements = new ArrayList<>();
        List<Advertisement> allAdvertisements = storage.list();
        for(Advertisement ad: allAdvertisements){
            if(ad.getHits() <= 0){
                notActiveAdvertisements.add(ad);
            }
        }
        Comparator<Advertisement> comparator = new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
        notActiveAdvertisements.sort(comparator);
        return notActiveAdvertisements;
    }
}
