package logic.table;

import javafx.scene.control.Tab;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NullTableTest {
    private Table table;
    @Before
    public void setUp() throws Exception {
        table = new NullTable();
    }

    @Test
    public void getTableName() {
        assertNull(table.getTableName());
    }

    @Test
    public void getNumberOfDropTargets() {
        assertEquals(0, table.getNumberOfDropTargets());
    }

    @Test
    public void getCurrentlyDroppedDropTargets() {
        assertEquals(0, table.getCurrentlyDroppedDropTargets());
    }

    @Test
    public void getBumpers() {
        assertTrue(table.getBumpers().isEmpty());
    }

    @Test
    public void getTargets() {
        assertTrue(table.getTargets().isEmpty());
    }

    @Test
    public void isPlayableTable() {
        assertFalse(table.isPlayableTable());
    }
}