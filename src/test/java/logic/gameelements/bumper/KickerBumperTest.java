package logic.gameelements.bumper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KickerBumperTest {

    private KickerBumper bumper;

    @Before
    public void setUp() throws Exception {
        bumper = new KickerBumper(0);
    }

    @Test
    public void getScore() {
        assertEquals(500, bumper.getScore());
        bumper.upgrade();
        assertEquals(1000, bumper.getScore());
    }
}