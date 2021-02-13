package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {
    public static enum HumanFactoryType{
        MALE, FEMALE;
    }
    //4. В FactoryProducer создай публичный статический метод getFactory с аргументом HumanFactoryType.
    //Этот метод должен возвращать одну из фабрик: для ключа MALE - MaleFactory, для FEMALE - FemaleFactory.
    //
    //Молодец, Фабрика фабрик готова! Это паттерн Abstract Factory.
    //В коде тебе не важно, какая фабрика используется.
    public static AbstractFactory getFactory(HumanFactoryType humanFactoryType){
        if(humanFactoryType.equals(HumanFactoryType.MALE)) return new MaleFactory();
        if(humanFactoryType.equals(HumanFactoryType.FEMALE)) return new FemaleFactory();
        return null;
    }
}
