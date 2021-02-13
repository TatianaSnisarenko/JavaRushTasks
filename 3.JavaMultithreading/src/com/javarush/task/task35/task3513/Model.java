package com.javarush.task.task35.task3513;

import java.util.*;

//Model - будет содержать игровую логику и хранить игровое поле, будет ответственен за все манипуляции производимые с игровым полем.
public class Model {
    //Создадим игровое поле
    private static final int FIELD_WIDTH = 4; // ширина игрового поля
    private Tile[][] gameTiles;// массив плиток поля
    int score;
    int maxTile;//максимальная плитка на поле
    private Stack<Integer> previousScores = new Stack<>();
    private Stack<Tile[][]> previousStates = new Stack<>();
    private boolean isSaveNeeded = true;

    //Давай реализуем метод autoMove в классе Model, который будет выбирать лучший из возможных ходов и выполнять его.
    //
    //Сделаем так:
    //1) Создадим локальную PriorityQueue с параметром Collections.reverseOrder() (для того, чтобы вверху очереди всегда был максимальный элемент)
    // и размером равным четырем.
    //2) Заполним PriorityQueue четырьмя объектами типа MoveEfficiency (по одному на каждый вариант хода).
    //3) Возьмем верхний элемент и выполним ход связанный с ним.
    //
    //После реализации метода autoMove добавим его вызов в метод keyPressed класса Controller по нажатию на клавишу A (код - KeyEvent.VK_A).
    //
    //P.S. В качестве факультативного задания можешь почитать про указатели на методы и попробовать передать аргумент в метод getMoveEfficiency
    // используя оператор "::". Для метода left должно получиться getMoveEfficiency(this::left). Альтернативно можешь использовать внутренний анонимный класс.

    public void autoMove(){
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4,Collections.reverseOrder());
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::down));

        priorityQueue.peek().getMove().move();


    }


    //1) boolean hasBoardChanged - будет возвращать true, в случае, если вес плиток в массиве gameTiles отличается от веса плиток в верхнем массиве стека previousStates.
    // Обрати внимание на то, что мы не должны удалять из стека верхний элемент, используй метод peek.

    public boolean hasBoardChanged(){
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                if(gameTiles[i][j].value != previousStates.peek()[i][j].value) return true;
            }

        }
        return false;
    }

    //MoveEfficiency getMoveEfficiency(Move move) - принимает один параметр типа move, и возвращает объект типа MoveEfficiency описывающий эффективность переданного хода.
    // Несколько советов:
    //а) не забудь вызывать метод rollback, чтобы восстановить корректное игровое состояние;
    //б) в случае, если ход не меняет состояние игрового поля, количество пустых плиток и счет у объекта MoveEfficiency сделай равными -1 и 0 соответственно;
    //в) выполнить ход можно вызвав метод move на объекте полученном в качестве параметра.

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency = new MoveEfficiency(0, 0, move);
        move.move();
        if(!hasBoardChanged()){
            moveEfficiency.setNumberOfEmptyTiles(-1);
            moveEfficiency.setScore(0);
        }else{
            moveEfficiency.setNumberOfEmptyTiles(getEmptyTiles().size());
            moveEfficiency.setScore(score);
        }
        rollback();
        return moveEfficiency;
    }

    //Дадим возможность игре самостоятельно выбирать следующий ход при нажатии клавиши R.
    //Начнем с простого, реализуем метод randomMove в классе Model, который будет вызывать один из методов движения случайным образом.
    // Можешь реализовать это вычислив целочисленное n = ((int) (Math.random() * 100)) % 4.
    //Это число будет содержать целое псевдослучайное число в диапазоне [0..3], по каждому из которых можешь вызывать один из методов left, right, up, down.

    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        if(n == 0) left();
        else if(n == 1) right();
        else if(n == 2) up();
        else if(n == 3) down();
    }

    //Приватный метод saveState с одним параметром типа Tile[][] будет сохранять текущее
    //игровое состояние и счет в стеки с помощью метода push и устанавливать флаг isSaveNeeded равным false.
    private void saveState(Tile[][] tiles){
        Tile[][] tilesCopy = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < tilesCopy.length; i++) {
            for (int j = 0; j < tilesCopy[i].length; j++) {
                tilesCopy[i][j] = new Tile();
            }

        }
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tilesCopy[i][j].value = tiles[i][j].value;

            }

        }
        previousStates.push(tilesCopy);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    //Публичный метод rollback будет устанавливать текущее игровое состояние равным последнему находящемуся в стеках с помощью метода pop
    public void rollback(){
        if(!previousStates.isEmpty() && !previousScores.isEmpty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public Model() {
        resetGameTiles();

    }

    public void resetGameTiles(){
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();

            }

        }
        addTile();
        addTile();
        score = 0;
        maxTile = 0;
    }

    private void addTile(){
        List<Tile> emptyTiles = getEmptyTiles();

        if(emptyTiles.size() > 0){
            emptyTiles.get((int)(emptyTiles.size() * Math.random())).value = Math.random() < 0.9 ? 2 : 4;
        }




    }

    private List<Tile> getEmptyTiles(){
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                if(gameTiles[i][j].value == 0){
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }


    //Сжатие плиток, таким образом, чтобы все пустые плитки были справа, т.е. ряд {4, 2, 0, 4} становится рядом {4, 2, 4, 0}
    private boolean compressTiles(Tile[] tiles){
        boolean isCompressed = false;

        for (int i = 0; i < tiles.length - 1; i++) {
            if(tiles[i].value == 0){
                for (int j = i + 1; j < tiles.length; j++) {
                    if(tiles[j].value != 0) {
                        Tile tempTile = tiles[i];
                        tiles[i] = tiles[j];
                        tiles[j] = tempTile;
                        isCompressed = true;
                        break;
                    }
                }
            }
        }
        return isCompressed;

    }
//Слияние плиток одного номинала, т.е. ряд {4, 4, 2, 0} становится рядом {8, 2, 0, 0}.
    private boolean mergeTiles(Tile[] tiles){
        boolean isMerged = false;

        for (int i = 0; i < tiles.length - 1; i++) {
            if(tiles[i].value != 0) {
                if (tiles[i].value == tiles[i + 1].value) {
                    tiles[i].value = tiles[i + 1].value * 2;
                    isMerged = true;
                    if(tiles[i].value > maxTile) maxTile = tiles[i].value;
                    score += tiles[i].value;
                    tiles[i + 1].value = 0;
                    compressTiles(tiles);


                }
            }
        }
        return isMerged;
    }

    //метод left будет для каждой строки массива gameTiles вызывать методы compressTiles и mergeTiles
    // и добавлять одну плитку с помощью метода addTile в том случае, если это необходимо.
    public void left(){
        if(isSaveNeeded) saveState(gameTiles);
        boolean isCompressed = false;
        boolean isMerged = false;
        for (int i = 0; i < gameTiles.length; i++) {
            Tile[] rowTiles = new Tile[gameTiles.length];
            for (int j = 0; j < rowTiles.length; j++) {
                rowTiles[j] = gameTiles[i][j];
            }
            isCompressed = compressTiles(rowTiles);
            isMerged = mergeTiles(rowTiles);
            for (int j = 0; j < rowTiles.length ; j++) {
                gameTiles[i][j] = rowTiles[j];

            }

        }
        if(isCompressed || isMerged){
            addTile();
        }
        isSaveNeeded = true;
    }

    //поворот массива на 90 градусов по часовой стрелке
    void turnArrayToTheRight(){
        Tile[][] tempTiles = new Tile[gameTiles.length][gameTiles[0].length];
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[0].length; j++) {
                //tempTile[i][FIELD_WIDTH - 1 - j] = gameTiles[j][i];
                tempTiles[i][gameTiles[i].length - 1 - j] = gameTiles[j][i];
            }
        }
        gameTiles = tempTiles.clone();
    }

    //Что будет, если повернуть двумерный массив на 90 градусов по часовой стрелке, сдвинуть влево, а потом еще трижды выполнить поворот?
    public void down(){
        saveState(gameTiles);
        turnArrayToTheRight();
        left();
        turnArrayToTheRight();
        turnArrayToTheRight();
        turnArrayToTheRight();
    }
    public void up(){
        saveState(gameTiles);
        turnArrayToTheRight();
        turnArrayToTheRight();
        turnArrayToTheRight();
        left();
        turnArrayToTheRight();
    }

    public void right(){
        saveState(gameTiles);
        turnArrayToTheRight();
        turnArrayToTheRight();
        left();
        turnArrayToTheRight();
        turnArrayToTheRight();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }


    public boolean canMove(){
        if(getEmptyTiles().size() > 0) return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length - 1; j++) {
                if(gameTiles[i][j].value == gameTiles[i][j+1].value) return true;
            }

        }

        for (int j = 0; j < gameTiles[0].length; j++) {
            for (int i = 0; i < gameTiles.length - 1; i++) {
                if(gameTiles[i][j].value == gameTiles[i+1][j].value) return true;
            }

        }

        return false;
    }
/* public void setGameTiles(Tile[][] tiles){
        gameTiles = tiles;
    }

    }*/

}
