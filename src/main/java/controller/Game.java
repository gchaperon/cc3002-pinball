package controller;

import logic.bonus.Bonus;
import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
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
    private List<Table> tables;
    private int currentTableIndex;
    private Bonus jackPotBonus;
    private Bonus extraBallBonus;
    private Bonus dropTargetBonus;

    public Game() {
        this(5);
    }

    public Game(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
        this.score = 0;
        this.tables = new ArrayList<Table>();
        this.currentTableIndex = -1; // game sin mesa
        this.jackPotBonus = new JackPotBonus();
        this.extraBallBonus = new ExtraBallBonus();
        this.dropTargetBonus = new DropTargetBonus();
        // mas cosas
    }

    public void extraBall() {
        this.numberOfBalls++;
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

}
