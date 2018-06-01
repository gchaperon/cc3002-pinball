package logic.table;

import logic.gameelements.bumper.Bumper;
import logic.gameelements.target.DropTarget;
import logic.gameelements.target.SpotTarget;
import logic.gameelements.target.Target;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConcreteTableTest {
    private ConcreteTable table;

    @Before
    public void setUp() throws Exception {
        table = new ConcreteTable(8002, "tab1", 10,
                0.5, 3, 3);
    }

    @Test
    public void getTableName() {
        assertEquals("tab1", table.getTableName());
    }

    @Test
    public void getNumberOfDropTargets() {
        assertEquals(3, table.getNumberOfDropTargets());
    }

    @Test
    public void getCurrentlyDroppedDropTargets() {
        Target dropTarget = table.getTargets().get(0);
        Target spotTarget = table.getTargets().get(table.getNumberOfDropTargets());
        dropTarget.hit();
        spotTarget.hit();
        assertEquals(1, table.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void setCurrentlyDroppedDropTargets() {
        table.getTargets().get(0).hit();
        table.getTargets().get(1).hit();
        assertEquals(2, table.getCurrentlyDroppedDropTargets());
        table.getTargets().get(0).reset();
        assertEquals(1, table.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void getBumpers() {
        assertEquals(10, table.getBumpers().size());
    }

    @Test
    public void getTargets() {
        assertEquals(6, table.getTargets().size());
        assertTrue(table.getTargets().get(0) instanceof DropTarget);
        assertTrue(table.getTargets().get(table.getNumberOfDropTargets()) instanceof SpotTarget);
    }

    @Test
    public void resetDropTargets() {
        assertEquals(0, table.getCurrentlyDroppedDropTargets());
        for (Target target :
                table.getTargets().subList(0, table.getNumberOfDropTargets())) {
            target.hit();
        }
        assertEquals(3, table.getCurrentlyDroppedDropTargets());
        for (Target target :
                table.getTargets().subList(0, table.getNumberOfDropTargets())) {
            assertFalse(target.isActive());
        }
        table.resetDropTargets();
        for (Target target :
                table.getTargets().subList(0, table.getNumberOfDropTargets())) {
            assertTrue(target.isActive());
        }
        assertEquals(0, table.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void upgradeAllBumpers() {
        for (Bumper bumper :
                table.getBumpers()) {
            assertFalse(bumper.isUpgraded());
        }
        table.upgradeAllBumpers();
        for (Bumper bumper :
                table.getBumpers()) {
            assertTrue(bumper.isUpgraded());
        }
    }

    @Test
    public void isPlayableTable() {
        assertTrue(table.isPlayableTable());
    }
}