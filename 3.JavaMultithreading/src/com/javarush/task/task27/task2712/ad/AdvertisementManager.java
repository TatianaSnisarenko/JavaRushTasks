package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private List<Advertisement> bestAdvertisement;
    private long bestPrice = 0;

    private int timeSeconds; //время выполненеия заказа поваром в секундах

    public AdvertisementManager(int timeSeconds) {  //конструктор принимает значение в минутах, а сохраняет в поле в секундах
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {        //подбор видео
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();    //проверка на наличие видео
        List<Advertisement>advertisementsWithHits = new ArrayList<>();
        for (Advertisement advertisement : storage.list())if (advertisement.getHits()>0)advertisementsWithHits.add(advertisement); //создал лист видео имеющих положительный hits

        makeAllSets(advertisementsWithHits); //отправляю список на рекурсию

        if(bestAdvertisement.isEmpty())
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
        else
            StatisticManager.getInstance().register(new VideoSelectedEventDataRow(bestAdvertisement, bestPrice, calcDuration(bestAdvertisement)));

        bestAdvertisement.sort(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int) (o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());  //сортировка по уменьшению стоимости показа одного ролика
            }
        }.thenComparing(new Comparator<Advertisement>() {

            @Override
            public int compare(Advertisement o1, Advertisement o2) {        //сортировка по увеличению стоимости показа одной секунды рекламного ролика в тысячных частях копейки
                return o2.getDuration() - o1.getDuration();
            }
        }));

        for (Advertisement advertisement : bestAdvertisement){      //вывод в консоль каждого ролика + метод revalidate()
            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "+ advertisement.getAmountPerOneDisplaying() + ", " + advertisement.getAmountPerSecond());
            advertisement.revalidate();
        }

    }

    private int calcDuration(List<Advertisement>list){      //рассчет продолжительности воспроизведения листа видео
        int sumDuration = 0;
        for (Advertisement advertisement : list)sumDuration += advertisement.getDuration();
        return sumDuration;
    }

    private long calcPrice(List<Advertisement>list){        //рассчет суммы выручки от листа восрпоизведения
        long sumPrice = 0;
        for (Advertisement advertisement : list)sumPrice += advertisement.getAmountPerOneDisplaying();
        return sumPrice;
    }

    private void checkSet(List<Advertisement>list){         //проверка листа
        if (bestAdvertisement == null){
            if (calcDuration(list) <= timeSeconds){
                bestAdvertisement = list;
                bestPrice = calcPrice(list);
            }
        }
        else{
            if (calcDuration(list) <= timeSeconds && calcPrice(list) > bestPrice){
                bestAdvertisement = list;
                bestPrice = calcPrice(list);

            }else if (calcDuration(list) <= timeSeconds && calcPrice(list) == bestPrice){
                if (calcDuration(list) > calcDuration(bestAdvertisement)){
                    bestAdvertisement = list;
                }else if (calcDuration(list) == calcDuration(bestAdvertisement)){
                    if (list.size() < bestAdvertisement.size()){
                        bestAdvertisement = list;
                    }
                }
            }
        }
    }

    public void makeAllSets(List<Advertisement> list){
        if (list.size() > 0)checkSet(list);

        for (int i = 0; i < list.size(); i++) {
            List<Advertisement>newSet = new ArrayList<>(list);
            newSet.remove(i);
            makeAllSets(newSet);
        }
    }
}
