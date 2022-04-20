package controllers;

import java.awt.Point;
import java.util.ArrayList;

import models.Model;
import models.Zone;
import models.roles.Player;
import models.roles.Role;
import views.View;

/**
 * ContrGrid
 */
public class ContrGrid extends Controller {
    private ContrFlooding contrFlooding;
    private ContrPlayer contrPlayer;

    public ContrGrid(Model model, View view, ContrFlooding contrFlooding) {
        super(model, view);
        this.contrFlooding = contrFlooding;
    }

    public void click(int x, int y) {
        if (model.getIsland().inMap(new Point(x, y)) && model.getState() != Model.State.LOSE) {
            if (contrFlooding.getEscape() != null) {
                clickEscape(x, y);
            } else if (model.getActPlayer().getState() == Player.State.MOVING) {
                if (model.getActPlayer().getRole() == Role.Navigateur) {
                    clickNavigator(x, y);
                } else {
                    clickMove(x, y);
                }
            } else if (model.getActPlayer().getState() == Player.State.DRY) {
                clickDry(x, y);
            }
        }
        this.view.repaint();
    }

    public void setContrPlayer(ContrPlayer contrPlayer) {
        this.contrPlayer = contrPlayer;
    }

    private Boolean victoryCheck() {
        for (Player p : model.getPlayers()) {
            if (p.getPosition() != model.getHeliZone()) {
                return false;
            }
        }
        return !model.getTreasureState().contains(false);
    }

    private void clickMove(int x, int y) {
        int[][] action = model.nbAction(model.getActPlayer());
        if (action[y][x] <= model.getActPlayer().getNbActions()
                && action[y][x] != 0
                && model.getIsland().getZone(x, y).getWaterLvl() < model.getIsland().getZone(x, y).getMaxWaterLvl()) {
            Zone moveZ = model.getIsland().getZone(x, y);
            model.getActPlayer().changePosition(moveZ);
            model.getActPlayer().setAction(model.getActPlayer().getNbActions() - action[y][x]);
            if (model.getActPlayer().getRole() == Role.Pilote) {
                model.getActPlayer().powerDown();
            } else if (model.getActPlayer().getRole() == Role.Ingenieur && !model.getActPlayer().getPower()) {
                model.getActPlayer().dryUp();
            }
            if (victoryCheck()) {
                model.setState(Model.State.VICTORY);
                view.gameOver();
            }
        }
    }

    private void clickNavigator(int x, int y) {
        if (contrPlayer.selectedPlayer == null || contrPlayer.selectedPlayer == null
                || contrPlayer.selectedPlayer == model.getActPlayer()) {
            clickMove(x, y);
        } else {
            int[][] action = model.nbActionWithoutPower(contrPlayer.selectedPlayer.getPosition().getX(),
                    contrPlayer.selectedPlayer.getPosition().getY());
            if (action[y][x] <= 2 && model.getActPlayer().getNbActions() > 0){
                model.getActPlayer().setAction(model.getActPlayer().getNbActions() - 1);
                contrPlayer.selectedPlayer.changePosition(model.getIsland().getZone(x, y));
            }
        }
    }

    private void clickDry(int x, int y) {
        if (model.getActPlayer().getNbActions() > 0) {
            Zone digZ = model.getIsland().getZone(x, y);
            ArrayList<Zone> digZones = new ArrayList<Zone>();
            ArrayList<Point> action = model.getActPlayer().neigboursDry(this.model);
            action.add(new Point(model.getActPlayer().getPosition().getX(),
                    model.getActPlayer().getPosition().getY()));
            for (Point point : action) {
                digZones.add(model.getIsland().getZone(point.x, point.y));
            }

            if (digZ.getWaterLvl() == 1 && digZones.contains(digZ)) {
                digZ.dry();
                model.getActPlayer().dryUp();
            }
        }
    }

    private void clickEscape(int x, int y) {
        ArrayList<Point> neigbours = contrFlooding.getEscape().neigboursMove(this.model);
        for (Point point : neigbours) {
            Zone zone = model.getIsland().getZone(point.x, point.y);
            if (zone != null && zone.getWaterLvl() != zone.getMaxWaterLvl() && x == zone.getX() && y == zone.getY()) {
                contrFlooding.getEscape().setState(Player.State.MOVING);
                contrFlooding.getEscape().changePosition(zone);
                contrFlooding.setEscape();
                if (contrFlooding.getEscape() == null) {
                    if (victoryCheck()) {
                        model.setState(Model.State.VICTORY);
                        view.gameOver();
                        return;
                    }
                    contrFlooding.flooding(contrFlooding.nbInondation);
                }
                return;
            }
        }
    }
}