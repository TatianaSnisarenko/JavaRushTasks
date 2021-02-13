package com.javarush.task.task35.task3513;

//Класс описывает эффективность хода.
//
// В нем нам понадобятся приватные поля numberOfEmptyTiles и score типа int, а также приватное поле поле move типа Move.
//В классе MoveEfficiency необходим конструктор с тремя параметрами (int numberOfEmptyTiles, int score, Move move)
// для инициализации полей класса и геттер для поля move.

public class MoveEfficiency implements Comparable<MoveEfficiency>{
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public void setNumberOfEmptyTiles(int numberOfEmptyTiles) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }
//В методе compareTo первым делом сравни количество пустых плиток (numberOfEmptyTiles), потом счет (score),
// если количество пустых плиток равное. Если и счет окажется равным, будем считать эффективность ходов равной и вернем ноль.

    @Override
    public int compareTo(MoveEfficiency o) {
        int result = Integer.compare(this.numberOfEmptyTiles, o.numberOfEmptyTiles);
        if(result != 0){
            return result;
        }else {
            return Integer.compare(this.score, o.score);
        }


    }
}
