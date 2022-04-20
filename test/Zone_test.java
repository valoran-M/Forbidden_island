import static org.junit.Assert.*;
import org.junit.*;

import models.Zone;

public class Zone_test {
    private Zone z;

    @Before
    public void initZone() {
        this.z = new Zone(2, 1);
    }

    @Test
    public void testGetter() {
        assertEquals((int) this.z.getCoord().getX(), 2);
        assertEquals((int) this.z.getCoord().getY(), 1);
        assertEquals(this.z.getMaxWaterLvl(), 2);
        assertEquals(this.z.getWaterLvl(), 0);
    }

    @Test
    public void testMethod() {
        this.z.drown();
        assertEquals(this.z.getWaterLvl(), 1);
        this.z.dry();
        assertEquals(this.z.getWaterLvl(), 0);
    }
}
