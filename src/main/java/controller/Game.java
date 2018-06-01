package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.table.NullTable;
import logic.table.Table;
import visitor.Visitor;

import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Gabriel Chaperon
 */
public class Game implements Observer {
    private int numberOfBalls;
    private int score;
    private Table table;
    private Bonus jackPotBonus;
    private Bonus extraBallBonus;
    private Bonus dropTargetBonus;

    public Game() {
        this(5);
    }

    public Game(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
        this.score = 0;
        this.table = new NullTable();

        this.jackPotBonus = new JackPotBonus();
        this.extraBallBonus = new ExtraBallBonus();
        this.dropTargetBonus = new DropTargetBonus();
        // mas cosas
    }

    public int addScore(int scoreToAdd) {
        this.score += scoreToAdd;
        return this.score;
    }

    public Table getCurrentTable() {
        return this.table;
    }

    public Bonus getJackPotBonus() {
        return this.jackPotBonus;
    }

    public Bonus getExtraBallBonus() {
        return extraBallBonus;
    }

    public Bonus getDropTargetBonus() {
        return dropTargetBonus;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.accept((Visitor) arg);
    }

    private void accept(Visitor visitor) {
        visitor.visitGame(this);
    }

    public int getNumberOfBalls() {
        return this.numberOfBalls;
    }

    public int dropBall() {
        return --this.numberOfBalls;
    }

    public int extraBall() {
        return ++this.numberOfBalls;
    }

    public int getCurrentScore() {
        return this.score;
    }

    public void setTable(Table newTable) {
        this.table = newTable;
    }

    public boolean isOver() {
        return this.numberOfBalls <= 0;
    }
}
