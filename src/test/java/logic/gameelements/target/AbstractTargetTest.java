package logic.gameelements.target;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractTargetTest {
    SpotTarget spotTarget;

    @Before
    public void setUp() throws Exception {
        spotTarget = new SpotTarget();
    }

    @Test
    public void isActive() {
        assertTrue(spotTarget.isActive());
    }

    @Test
    public void reset() {
        spotTarget.hit();
        assertFalse(spotTarget.isActive());
        spotTarget.reset();
        assertTrue(spotTarget.isActive());
    }
}