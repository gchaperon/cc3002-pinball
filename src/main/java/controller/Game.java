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
 * Game logic controller class. This class is in charge of managing the interactions
 * between all the elements that present in a game of pinball.
 * It is also the class that keeps track of the points the players at every moment
 * and the amount of balls there are left.
 * Finally it also has the task to determine if the game has endededed or not.
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

    /**
     * Creates a new Game object with 5 balls, which is considered to be
     * default.
     */
    public Game() {
        this(3);
    }

    /**
     * Creates a new Game object and lets the user decide the initial number
     * of balls.
     *
     * @param numberOfBalls The number of balls for the new game
     */
    public Game(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
        this.score = 0;
        this.table = new NullTable();

        this.jackPotBonus = new JackPotBonus();
        this.extraBallBonus = new ExtraBallBonus();
        this.dropTargetBonus = new DropTargetBonus();
        // mas cosas
    }

    /**
     * Ads a value to the current score of the game.
     *
     * @param scoreToAdd    The score to add.
     * @return  the new score.
     */
    public int addScore(int scoreToAdd) {
        this.score += scoreToAdd;
        return this.score;
    }

    /**
     * Gets the current table of the game.
     *
     * @return  the current table of the game.
     */
    public Table getCurrentTable() {
        return this.table;
    }

    /**
     * Gets the instance of {@link logic.bonus.JackPotBonus} currently in the game.
     *
     * @return the DropTargetBonus instance.
     */
    public Bonus getJackPotBonus() {
        return this.jackPotBonus;
    }

    /**
     * Gets the instance of {@link logic.bonus.ExtraBallBonus} currently in the game.
     *
     * @return the ExtraBallBonus instance.
     */
    public Bonus getExtraBallBonus() {
        return extraBallBonus;
    }

    /**
     * Gets the instance of {@link logic.bonus.DropTargetBonus} currently in the game.
     *
     * @return the DropTargetBonus instance.
     */
    public Bonus getDropTargetBonus() {
        return dropTargetBonus;
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param   o     the observable object.
     * @param   arg   an argument passed to the <code>notifyObservers</code>
     *                 method.
     */
    @Override
    public void update(Observable o, Object arg) {
        this.accept((Visitor) arg);
    }

    /**
     * Method that accepts a {@link visitor.Visitor} and uses double dispatch.
     *
     * @param visitor   The visitor that will be given this game to trigger and
     *                  will trigger its specific actions.
     */
    private void accept(Visitor visitor) {
        visitor.visitGame(this);
    }

    /**
     * Returns the number of current balls in the game.
     *
     * @return the number of current balls in the game.
     */
    public int getNumberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * Method that decreases the number of current balls in the game by one.
     *
     * @return the number of balls after dropping one.
     */
    public int dropBall() {
        return (this.numberOfBalls > 0) ? --this.numberOfBalls : 0;
    }

    /**
     * Method that adds one ball to the game.
     *
     * @return  the new number of balls.
     */
    public int extraBall() {
        return ++this.numberOfBalls;
    }

    /**
     * Getter method for the current score.
     *
     * @return the current score of the game.
     */
    public int getCurrentScore() {
        return this.score;
    }

    /**
     * Sets a new table in the game.
     *
     * @param newTable  The new table to be set as the current one.
     */
    public void setTable(Table newTable) {
        this.table = newTable;
    }

    /**
     * The game is over when there are no more balls left in the game.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isOver() {
        return this.numberOfBalls <= 0;
    }
}
