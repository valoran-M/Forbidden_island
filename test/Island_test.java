import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.*;

import models.Island;


public class Island_test {
    private Island islands;

    @Before
    public void Island_init() {
        this.islands = new Island();
    }

    @Test
    public void testGetter(){
        assertEquals(this.islands.getWidth(), 6);
        assertEquals(this.islands.getHeight(), 6);
        assertEquals(this.islands.getGridSize(), new Point(6,6));
        assertTrue(this.islands.inMap(this.islands.getRandomCase().getCoord()));

        ArrayList<Integer> tab1 = new ArrayList<Integer>();
        tab1.add(2);
        tab1.add(3);

        ArrayList<Integer> tab2 = new ArrayList<Integer>(tab1);
        tab2.add(1);
        tab2.add(4);
        Collections.sort(tab2);

        ArrayList<Integer> tab3 = new ArrayList<Integer>(tab2);
        tab3.add(0);
        tab3.add(5);
        Collections.sort(tab3);

        ArrayList<ArrayList<Integer>> tab = new ArrayList<ArrayList<Integer>>();
        tab.add(tab1);
        tab.add(tab2);
        tab.add(tab3);



        for(int i = 0; i < 3; i++){
            assertEquals(this.islands.getCoordLine(i), tab.get(i));
            assertEquals(this.islands.getCoordLine(5 - i), tab.get(i));
        }

        for(int x = 0; x < this.islands.getWidth(); x++){
            for(int y = 0; y < this.islands.getHeight(); y++){
                if(this.islands.inMap(new Point(x,y))){
                    assertEquals(this.islands.getZone(x,y), this.islands.getGrid().get(y).get(x));
                } else {
                    assertEquals(this.islands.getZone(x, y), null);
                }
            }
        }
    }

    @Test
    public void testMethod(){
        assertTrue(this.islands.inMap(new Point(3,3)));
        assertFalse(this.islands.inMap(new Point(0,0)));
    }
}
