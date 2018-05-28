package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.table.ConcreteTable;
import logic.table.NullTable;
import logic.table.Table;
import visitor.BonusVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {
    private int numberOfBalls;
    private int score;
    private long seed;
    private List<Table> tables;
    private int currentTableIndex;
    private Bonus jackPotBonus;
    private Bonus extraBallBonus;
    private Bonus dropTargetBonus;

    public Game() {
        this(5, System.currentTimeMillis());
    }

    public Game(long seed) {
        this(5, seed);
    }

    public Game(int numberOfBalls, long seed) {
        this.numberOfBalls = numberOfBalls;
        this.score = 0;
        this.seed = seed;

        this.tables = new ArrayList<Table>();
        this.tables.add(new NullTable());
        this.currentTableIndex = 0;

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
        return this.tables.get(this.currentTableIndex);
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
        this.accept((BonusVisitor) arg);
    }

    private void accept(BonusVisitor visitor) {
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
        if (tables.contains(newTable)) {
            this.currentTableIndex = tables.indexOf(newTable);
        }
        else {
            tables.add(newTable);
            this.currentTableIndex = tables.size() - 1;
        }
    }

    public boolean isOver() {
        return this.numberOfBalls <= 0;
    }
}
