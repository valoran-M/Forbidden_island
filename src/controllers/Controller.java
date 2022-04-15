package controllers;

import java.util.ArrayList;

import models.Model;
import models.Player;
import models.Zone;

/**
 * Controllers
 */
public abstract class Controller {
    protected Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    private Boolean escape(Zone zone) {
        ArrayList<Zone> neighbours = model.getIsland().neighbours(zone);
        for (Zone neigZ : neighbours) {
            if (neigZ != null && neigZ.getWaterLvl() != neigZ.getMaxWaterLvl()) {
                return true;
            }
        }

        return false;
    }

    public Boolean flooding(int nb) {
        Boolean gameOver = false;
        for (int i = 0; i < nb; i++) {
            if (model.getPioche().getNbCarte() == 0) {
                model.getPioche().resetPioche();
            }
            Zone drownZ = model.getPioche().draw();
            drownZ.drown();
            model.pick();
            Boolean escape = false;
            for (Player p : model.getPlayers()) {
                if (drownZ == p.getPosition()) {
                    if (drownZ.getWaterLvl() == drownZ.getMaxWaterLvl()) {
                        if (escape(drownZ)) {
                            escape = true;
                            p.setState(Player.State.ESCAPE);
                        } else {
                            gameOver = true;
                        }
                    }
                }
            }
            if (escape) {
                model.setEscape();
                return gameOver;
            }
            if (gameOver) {
                return gameOver;
            }
        }
        model.resetNbInondation();
        return gameOver;
    }
}