package controllers;

import java.awt.Point;
import java.util.ArrayList;

import models.Model;
import models.Zone;
import models.Player;
import views.View;

/**
 * ContrGrid
 */
public class ContrGrid extends Controller {
    View view;

    public ContrGrid(Model model, View view) {
        super(model);
        this.view = view;
    }

    public void click(int x, int y) {
        if (model.getIsland().inMap(new Point(x, y))) {
            if (model.getEscape() != null) {
                clickEscape(x, y);
            } else if (model.getActPlayer().getState() == Player.State.MOVING) {
                clickMove(x, y);
            } else if (model.getActPlayer().getState() == Player.State.DIGGING) {
                clickDry(x, y);
            }
        }
    }

    private void clickMove(int x, int y) {
        int[][] action = model.nbActionMove();
        if (action[y][x] <= model.getActPlayer().getNbActions()) {
            Zone moveZ = model.getIsland().getZone(x, y);
            model.getActPlayer().changePosition(moveZ);
            model.getActPlayer().setAction(model.getActPlayer().getNbActions() - action[y][x]);
        }
        this.view.grid.repaint();
    }

    private void clickDry(int x, int y) {
        if (model.getActPlayer().getNbActions() > 0) {
            Zone digZ = model.getIsland().getZone(x, y);
            Zone actZ = model.getActPlayer().getPosition();
            ArrayList<Zone> digZones = model.getIsland().neighbours(actZ);
            digZones.add(model.getActPlayer().getPosition());
            if (digZ.getWaterLvl() == 1 && digZones.contains(digZ)) {
                digZ.dry();
                model.getActPlayer().dryUp();
                view.repaint();
            }
        }
    }

    private void clickEscape(int x, int y) {
        ArrayList<Point> neigbours = model.getEscape().neigbours();
        for (Point point : neigbours) {
            Zone zone = model.getIsland().getZone(point.x, point.y);
            if (zone != null && zone.getWaterLvl() != zone.getMaxWaterLvl() && x == zone.getX() && y == zone.getY()) {
                model.getEscape().setState(Player.State.MOVING);
                model.getEscape().changePosition(zone);
                model.setEscape();
                if(model.getEscape() == null && flooding(model.getNbInondation())) {
                    view.gameOver();
                    return;
                }
                view.repaint();
                return;
            }
        }
    }
}