package logic.bonus;

import controller.Game;
import logic.table.ConcreteTable;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractBonusTest {

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
    public void timesTriggered() {
        game.getDropTargetBonus().trigger(game);
        game.getDropTargetBonus().trigger(game);
        game.getDropTargetBonus().trigger(game);
        game.getDropTargetBonus().trigger(game);
        game.getDropTargetBonus().trigger(game);
        assertEquals(5, game.getDropTargetBonus().timesTriggered());
    }
}