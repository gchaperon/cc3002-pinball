package logic.gameelements.bumper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PopBumperTest {
    PopBumper bumper;

    @Before
    public void setUp() throws Exception {
        bumper = new PopBumper(0);
    }

    @Test
    public void getScore() {
        assertEquals(100, bumper.getScore());
        bumper.upgrade();
        assertEquals(300, bumper.getScore());
    }
}