package com.javarush.task.task29.task2909.car;

public class Truck extends Car {
    public Truck(int numberOfPassengers) {
        super(TRUCK, numberOfPassengers);
    }
    //Необходимо переопределить метод getMaxSpeed() в классе Truck. В методе нужно использовать константную переменную метода MAX_TRUCK_SPEED, значение которой равно 80.

    @Override
    public int getMaxSpeed() {
        final int MAX_TRUCK_SPEED = 80;
        return MAX_TRUCK_SPEED;
    }
}
