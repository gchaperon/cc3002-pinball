package logic.gameelements.target;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.table.ConcreteTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpotTargetTest {

    private Game game;
    private ConcreteTable tab1;
    private SpotTarget spotTarget;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        tab1 = new ConcreteTable(8002,"tab1", 0,
                0, 1, 0);
        tab1.addObserver(game);
        game.setTable(tab1);
        spotTarget = (SpotTarget) game.getCurrentTable().getTargets().get(0);
    }

    @Test
    public void hitMakeInactive() {
        spotTarget.hit();
        assertFalse(spotTarget.isActive());
    }

    @Test
    public void hitTriggerBonus() {
        spotTarget.hit();
        assertEquals(1, game.getJackPotBonus().timesTriggered());
        assertEquals(100000, game.getCurrentScore());
    }
    @Test
    public void getScore() {
        assertEquals(0, spotTarget.getScore());
        spotTarget.hit();
        assertFalse(spotTarget.isActive());
        assertEquals(0, spotTarget.getScore());
    }
}