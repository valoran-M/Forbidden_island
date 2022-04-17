package test;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

import models.Island;


public class Island_test {
    private ArrayList<Island> islands;

    public Island_test(){
        Island ile = new Island();
        islands.add(ile);
    }

    private void testGetter(){
        assert this.islands.get(0).getWidth() == 6;
        assert this.islands.get(0).getHeight() == 6;
        assert this.islands.get(0).getGridSize() == new Point(6,6);
        assert this.islands.get(0).inMap(this.islands.get(0).getRandomCase().getCoord());

        ArrayList<Integer> tab1 = new ArrayList<Integer>();
        tab1.add(2);
        tab1.add(3);

        ArrayList<Integer> tab2 = new ArrayList<Integer>();
        tab2.addAll(tab1);
        tab2.add(1);
        tab2.add(4);
        Collections.sort(tab2);

        ArrayList<Integer> tab3 = new ArrayList<Integer>();
        tab2.addAll(tab1);
        tab2.add(0);
        tab2.add(5);
        Collections.sort(tab3);

        ArrayList<ArrayList<Integer>> tab = new ArrayList<ArrayList<Integer>>();
        tab.add(tab1);
        tab.add(tab2);
        tab.add(tab3);


        for(int i = 0; i < 3; i++){
            assert this.islands.get(0).getCoordLine(i) == tab.get(i);
            assert this.islands.get(0).getCoordLine(i) == tab.get(6 - i);
        }

        for(int x = 0; x < this.islands.get(0).getWidth(); x++){
            for(int y = 0; y < this.islands.get(0).getHeight(); y++){
                if(this.islands.get(0).inMap(new Point(x,y))){
                    assert this.islands.get(0).getZone(x,y) == this.islands.get(0).getGrid().get(x).get(y);
                } else {
                    assert this.islands.get(0).getZone(x, y) == null;
                }
            }
        }
    }

    private void testMethod(){
        assert this.islands.get(0).inMap(new Point(3,3));
        assert !this.islands.get(0).inMap(new Point(0,0));
    }

    public void test(){
        this.testGetter();
        this.testMethod();
    }
}
