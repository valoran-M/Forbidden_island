package controllers;

import java.util.ArrayList;

import models.Model;
import models.Player;
import models.Zone;

public abstract class ContrEscape extends Controller {
    protected static int nbInondation;

    public ContrEscape(Model model) {
        super(model);
        nbInondation = 3;
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
            nbInondation--;
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
        nbInondation = 3;
        return gameOver;
    }
}
