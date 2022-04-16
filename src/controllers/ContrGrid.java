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
    private ContrFlooding contrFlooding;

    public ContrGrid(Model model, View view, ContrFlooding contrFlooding) {
        super(model, view);
        this.contrFlooding = contrFlooding;
    }

    public void click(int x, int y) {
        if (model.getIsland().inMap(new Point(x, y)) && model.getState() != Model.State.LOSE) {
            if (contrFlooding.getEscape() != null) {
                clickEscape(x, y);
            } else if (model.getActPlayer().getState() == Player.State.MOVING) {
                clickMove(x, y);
            } else if (model.getActPlayer().getState() == Player.State.DIGGING) {
                clickDry(x, y);
            }
        }
        this.view.repaint();
    }

    private Boolean victoryCheck(){
        for (Player p : model.getPlayers()) {
            if (p.getPosition() != model.getHeliZone()) {
                return false;
            }
        }
        System.out.println("OK");
        return !model.getTreasureState().contains(false);
    }

    private void clickMove(int x, int y) {
        int[][] action = model.nbActionMove();
        if (action[y][x] <= model.getActPlayer().getNbActions()) {
            Zone moveZ = model.getIsland().getZone(x, y);
            model.getActPlayer().changePosition(moveZ);
            model.getActPlayer().setAction(model.getActPlayer().getNbActions() - action[y][x]);
            if(victoryCheck()){
                model.setState(Model.State.VICTORY);
                view.gameOver();
            }
        }
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
            }
        }
    }

    private void clickEscape(int x, int y) {
        ArrayList<Point> neigbours = contrFlooding.getEscape().neigbours();
        for (Point point : neigbours) {
            Zone zone = model.getIsland().getZone(point.x, point.y);
            if (zone != null && zone.getWaterLvl() != zone.getMaxWaterLvl() && x == zone.getX() && y == zone.getY()) {
                contrFlooding.getEscape().setState(Player.State.MOVING);
                contrFlooding.getEscape().changePosition(zone);
                contrFlooding.setEscape();
                if (contrFlooding.getEscape() == null) {
                    contrFlooding.flooding(contrFlooding.nbInondation);
                }
                return;
            }
        }
    }
}