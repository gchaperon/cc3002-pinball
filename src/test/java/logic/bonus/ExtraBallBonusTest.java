package logic.bonus;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.table.ConcreteTable;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExtraBallBonusTest {

    private Game game;
    private Table tab1;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        tab1 = new ConcreteTable(8008, "tab1", 10,
                0.5, 0, 0);
        game.setTable(tab1);
    }

    @Test
    public void trigger() {
        assertEquals(5, game.getNumberOfBalls());
        game.getExtraBallBonus().trigger(game);
        assertEquals(6, game.getNumberOfBalls());
    }
}