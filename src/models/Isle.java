package models;

import java.util.ArrayList;

import views.ViewCase;
import views.ViewGrid;

/**
 * Ile
 */
public class Isle extends ViewGrid {

    private ArrayList<ArrayList<Zone>> grid;

    public Isle(int size) {
        super(size, size);

        grid = new ArrayList<ArrayList<Zone>>();
        for (int j = 0; j < size; j++) {
            ArrayList<Zone> line = new ArrayList<Zone>();
            for (int i = 0; i < size; i++) {
                if (Math.abs(i - (size - 1) / 2.) +
                        Math.abs(j - (size - 1) / 2.) <= size / 2.) {
                    line.add(new Zone(50, 50));
                    addElem(line.get(i));
                } else {
                    line.add(null);
                    addElem(new ViewCase(50, 50, true));
                }
            }
            grid.add(line);
        }
    }
}
