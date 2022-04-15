package controllers;

import java.awt.Point;

import models.Model;
import models.Zone;
import models.Player;
import views.ViewGrid;

/**
 * ContrGrid
 */
public class ContrGrid extends Controller {
    ViewGrid view;

    public ContrGrid(Model model, ViewGrid view) {
        super(model);
        this.view = view;
    }

    public void click(int x, int y) {
        if (model.getIsland().inMap(new Point(x, y))) {
            if (model.getActPlayer().getState() == Player.State.MOVING) {
                int[][] action = model.nbActionMove();
                if (action[y][x] <= model.getActPlayer().getNbActions()) {
                    Zone moveZ = model.getIsland().getZone(x, y);
                    model.getActPlayer().changePosition(moveZ);
                    model.getActPlayer().setAction(model.getActPlayer().getNbActions() - action[y][x]);
                }
                view.repaint();
            }
        }
    }
}