package logic.bonus;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.table.ConcreteTable;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DropTargetBonusTest {
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
        assertEquals(0, game.getCurrentScore());
        game.getDropTargetBonus().trigger(game);
        assertEquals(1000000, game.getCurrentScore());
        for (Bumper bumper :
                game.getCurrentTable().getBumpers()) {
            assertTrue(bumper.isUpgraded());
        }
    }
}