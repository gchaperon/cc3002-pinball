package logic.gameelements.bumper;

import controller.Game;
import logic.table.ConcreteTable;
import logic.table.Table;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractBumperTest {

    private Game game;
    private ConcreteTable tab1;
    private Bumper bumper;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        tab1 = new ConcreteTable(8002,"tab1", 10,
                0.5, 0, 0);
        tab1.addObserver(game);
        game.setTable(tab1);
        bumper = game.getCurrentTable().getBumpers().get(0);
    }

    @Test
    public void remainingHitsToUpgrade() {
        assertEquals(3, bumper.remainingHitsToUpgrade());
    }

    @Test
    public void isUpgraded() {
        assertFalse(bumper.isUpgraded());
    }

    @Test
    public void upgrade() {
        bumper.upgrade();
        assertTrue(bumper.isUpgraded());
        assertEquals(0, game.getCurrentScore());
    }

    @Test
    public void downgrade() {
        bumper.upgrade();
        bumper.downgrade();
        assertFalse(bumper.isUpgraded());
    }

    @Test
    public void hitAddScore() {
        bumper.hit();
        assertEquals(100, game.getCurrentScore());
        bumper.upgrade();
        bumper.hit();
        assertEquals(400, game.getCurrentScore());
    }

    @Test
    public void hitUpgrade() {
        bumper.hit();
        bumper.hit();
        bumper.hit();
        assertTrue(bumper.isUpgraded());
    }

    @Test
    public void hitTriggerBonus() {
        bumper.hit();
        bumper.hit();
        bumper.hit();
        bumper.downgrade();
        bumper.hit();
        bumper.hit();
        bumper.hit();
        bumper.downgrade();
        bumper.hit();
        bumper.hit();
        bumper.hit();
        assertEquals(6, game.getNumberOfBalls());
    }
}