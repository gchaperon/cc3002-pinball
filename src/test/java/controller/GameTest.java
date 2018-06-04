package controller;

import logic.bonus.DropTargetBonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.JackPotBonus;
import logic.gameelements.target.DropTarget;
import logic.table.ConcreteTable;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game(3);
    }

    @Test
    public void getCurrentScore() {
        assertEquals(0, game.getCurrentScore());
    }

    @Test
    public void addScore() {
        game.addScore(100);
        assertEquals(100, game.getCurrentScore());
    }

    @Test
    public void getCurrentTable() {
        assertTrue(game.getCurrentTable() instanceof Table);
    }

    @Test
    public void getJackPotBonus() {
        assertTrue(game.getJackPotBonus() instanceof JackPotBonus);
    }

    @Test
    public void getExtraBallBonus() {
        assertTrue(game.getExtraBallBonus() instanceof ExtraBallBonus);
    }

    @Test
    public void getDropTargetBonus() {
        assertTrue(game.getDropTargetBonus() instanceof DropTargetBonus);
    }

    @Test
    public void getNumberOfBalls() {
        assertEquals(3, game.getNumberOfBalls());
    }

    @Test
    public void dropBall() {
        assertEquals(2, game.dropBall());
        game.dropBall();
        assertEquals(0, game.dropBall());
        assertEquals(0, game.dropBall());
    }

    @Test
    public void extraBall() {
        game.extraBall();
        assertEquals(4, game.getNumberOfBalls());
    }

    @Test
    public void setTable() {
        Table newTable = new ConcreteTable(0, "newTable", 0,
                0, 0, 0);
        game.setTable(newTable);
        assertEquals(newTable, game.getCurrentTable());
    }

    @Test
    public void isOver() {
        game.dropBall();
        game.dropBall();
        assertFalse(game.isOver());
        game.dropBall();
        assertTrue(game.isOver());
    }
}