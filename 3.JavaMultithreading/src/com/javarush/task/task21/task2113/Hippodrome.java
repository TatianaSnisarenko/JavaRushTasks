package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    public static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void move(){
        for(Horse horse: horses){
            horse.move();
        }
    }
    public void print(){
        for(Horse horse: horses){
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();

        }
    }
    public void run() {
        for (int i = 0; i < 100; i++) {

        move();
        print();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }

    public Horse getWinner(){
        Horse winner = horses.get(0);
        for(Horse horse : horses){
            if(horse.getDistance() > winner.getDistance()){
                winner = horse;
            }
        }

        return winner;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

    public static void main(String[] args) {

        game = new Hippodrome(new ArrayList<>());
        Horse one = new Horse("One", 3.0, 0.0);
        Horse two = new Horse("Two", 3.0, 0.0);
        Horse three = new Horse("Three", 3.0, 0.0);
        game.getHorses().add(one);
        game.getHorses().add(two);
        game.getHorses().add(three);

        game.run();

        game.printWinner();



    }
}
