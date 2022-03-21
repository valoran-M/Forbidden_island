package models;

import java.util.ArrayList;

/**
 * Ile
 */
public class Ile {

    private ArrayList<ArrayList<Zone>> grid;

    public Ile(float size) {
        grid = new ArrayList<ArrayList<Zone>>();
        for (int j = 0; j < size; j++) {
            ArrayList<Zone> a = new ArrayList<Zone>();
            for (int i = 0; i < size; i++) {
                if (Math.abs(i - (size - 1) / 2) +
                        Math.abs(j - (size - 1) / 2) <= size / 2) {
                    a.add(new Zone());
                } else {
                    a.add(null);
                }
            }
            System.out.println();
            grid.add(a);
        }
    }
}
