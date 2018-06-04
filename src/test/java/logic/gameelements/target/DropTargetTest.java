package logic.gameelements.target;

import controller.Game;
import logic.gameelements.bumper.Bumper;
import logic.table.ConcreteTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DropTargetTest {

    private Game game;
    private ConcreteTable tab1;
    private DropTarget dropTarget;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        tab1 = new ConcreteTable(8008,"tab1", 10,
                0, 0, 3);
        tab1.addObserver(game);
        game.setTable(tab1);
        dropTarget = (DropTarget) game.getCurrentTable().getTargets().get(0);
    }

    @Test
    public void hitAddScore() {
        assertTrue(dropTarget.isActive());
        assertEquals(0, game.getCurrentScore());
        dropTarget.hit();
        assertEquals(100, game.getCurrentScore());
        dropTarget.hit();
        assertEquals(100, game.getCurrentScore());
    }

    @Test
    public void hitTriggerExtraBallBonus() {
        dropTarget.hit();
        assertEquals(1, game.getExtraBallBonus().timesTriggered());
        dropTarget.hit();
    }

    @Test
    public void hitTriggerDropTargetBonus() {
        assertEquals(0, game.getDropTargetBonus().timesTriggered());
        assertEquals(3, game.getCurrentTable().getTargets().size());
        for (Target target:
             game.getCurrentTable().getTargets()) {
            assertTrue(target.isActive());
        }
        for (Bumper bumper :
                game.getCurrentTable().getBumpers()) {
            assertFalse(bumper.isUpgraded());
        }

        for (Target target:
                game.getCurrentTable().getTargets()) {
            target.hit();
        }

        assertEquals(1, game.getDropTargetBonus().timesTriggered());
        assertEquals(1000300, game.getCurrentScore());
        for (Target target:
                game.getCurrentTable().getTargets()) {
            assertFalse(target.isActive());
        }
        for (Bumper bumper :
                game.getCurrentTable().getBumpers()) {
            assertTrue(bumper.isUpgraded());
        }

        for (Target target:
                game.getCurrentTable().getTargets()) {
            target.reset();
        }

        for (Target target:
                game.getCurrentTable().getTargets()) {
            target.hit();
        }

        assertEquals(2, game.getDropTargetBonus().timesTriggered());
        assertEquals(2000600, game.getCurrentScore());
    }

    @Test
    public void getScore() {
        assertEquals(100, dropTarget.getScore());
        dropTarget.hit();
        assertEquals(0, dropTarget.getScore());
    }
}