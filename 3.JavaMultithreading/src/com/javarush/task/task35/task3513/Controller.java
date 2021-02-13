package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_ESCAPE;

//Controller - будет следить за нажатием клавиш во время игры.
//Контроллер, в свою очередь, будет в основном использоваться для обработки пользовательского ввода с клавиатуры,
// поэтому сделаем его наследником класса KeyAdapter.
public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private static final int WINNING_TILE = 2048;

    public View getView() {
        return view;
    }

    //Для начала нам понадобится конструктор, он будет принимать один параметр типа Model, инициализировать поле model,
    // а также сохранять в поле view новый объект типа View с текущим контроллером(this) в качестве параметра конструктора.


    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    //Далее, нам нужен метод resetGame, который позволит вернуть игровое поле в начальное состояние.
    //Необходимо обнулить счет, установить флаги isGameWon и isGameLost у представления в false и вызывать метод resetGameTiles у модели.

    public void resetGame(){
        model.score = 0;
        view.isGameWon = false;
        view.isGameLost = false;
        model.resetGameTiles();
    }

    public Tile[][] getGameTiles(){
        return model.getGameTiles();
    }

    public int getScore(){
        return model.score;
    }

    //Ну а теперь, самое главное! Для того чтобы иметь возможность обрабатывать пользовательский ввод,
    // необходимо переопределить метод keyPressed с одним параметром типа KeyEvent.
    //
    //Логика метода должна быть следующей:
    //1. Если была нажата клавиша ESC - вызови метод resetGame.
    //2. Если метод canMove модели возвращает false - установи флаг isGameLost в true.
    //3. Если оба флага isGameLost и isGameWon равны false - обработай варианты движения:
    //а) для клавиши KeyEvent.VK_LEFT вызови метод left у модели;
    //б) для клавиши KeyEvent.VK_RIGHT вызови метод right у модели;
    //в) для клавиши KeyEvent.VK_UP вызови метод up у модели;
    //г) для клавиши KeyEvent.VK_DOWN вызови метод down у модели.
    //4. Если поле maxTile у модели стало равно WINNING_TILE, установи флаг isGameWon в true.
    //5. В самом конце, вызови метод repaint у view.
    //
    //P.S. Для получения кода нажатой клавиши используй метод getKeyCode класса KeyEvent.


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) model.autoMove();
        if(e.getKeyCode() == KeyEvent.VK_R) model.randomMove();
        if(e.getKeyCode() == KeyEvent.VK_Z) model.rollback();
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            resetGame();
        if(!model.canMove()) view.isGameLost = true;
        if(view.isGameLost == false && view.isGameWon == false){
            if(e.getKeyCode() == KeyEvent.VK_LEFT) model.left();
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT) model.right();
            else if(e.getKeyCode() == KeyEvent.VK_UP) model.up();
            else if(e.getKeyCode() == KeyEvent.VK_DOWN) model.down();
        }
        if(model.maxTile == WINNING_TILE) view.isGameWon = true;
        view.repaint();

        //super.keyPressed(e);
    }
}
