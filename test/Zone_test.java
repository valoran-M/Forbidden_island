package test;

import models.Zone;
import java.awt.Point;

public class Zone_test {
    private Zone z;

    public Zone_test(){
        this.z = new Zone(2, 1);
    }

    private void testGetter(){
        assert this.z.getCoord() == new Point(2,1);
        assert this.z.getMaxWaterLvl() == 2;
        assert this.z.getWaterLvl() == 0;
        assert this.z.getX() == 2;
        assert this.z.getY() == 1;
    }

    private void testMethod(){
        this.z.drown();
        assert this.z.getWaterLvl() == 1;
        this.z.dry();
        assert this.z.getWaterLvl() == 0;
    }

    public void test(){
        this.testGetter();
        this.testMethod();
        System.out.print("test Zone : OK");
    }
}
