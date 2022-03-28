package models;

import java.util.ArrayList;

import views.Case;
import views.ViewGrid;

/**
 * Ile
 */
public class Ile extends ViewGrid {

    private ArrayList<ArrayList<Zone>> grid;

    public Ile(int size) {
        super(size, size);

        grid = new ArrayList<ArrayList<Zone>>();
        for (int j = 0; j < size; j++) {
            ArrayList<Zone> line = new ArrayList<Zone>();
            for (int i = 0; i < size; i++) {
                if (Math.abs(i - (size - 1) / 2.) +
                        Math.abs(j - (size - 1) / 2.) <= size / 2.) {
                    line.add(new Zone(50, 50));
                    this.addElem(line.get(i));
                } else {
                    line.add(null);
                    this.addElem(new Case(50, 50, true));
                }
            }
            grid.add(line);
        }
    }
}
