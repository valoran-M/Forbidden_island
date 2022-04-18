package controllers;

import java.util.ArrayList;

import models.Model;
import models.Zone;
import models.roles.Player;
import views.View;

public class ContrFlooding extends Controller {
    public int nbInondation;
    private Player escape;

    public ContrFlooding(Model model, View view) {
        super(model, view);
        nbInondation = 3;
        this.escape = null;
    }

    public Player getEscape() {
        return this.escape;
    }

    public void setEscape() {
        Player escape = null;
        for (Player player : model.getPlayers()) {
            if (player.getState() == Player.State.ESCAPE) {
                escape = player;
            }
        }
        this.escape = escape;
    }

    private Boolean escape(Zone zone, Player player) {
        ArrayList<Zone> neighbours = model.getIsland().neighbours(zone);
        
        for (Zone neigZ : neighbours) {
            if (neigZ != null && neigZ.getWaterLvl() < neigZ.getMaxWaterLvl()) {
                return true;
            }
        }
        return false;
    }

    private Boolean gameOverCase(Zone zone) {
        return model.getTemple().contains(zone) ||
                model.getHeliZone() == zone;
    }

    public void flooding(int nb) {
        for (int i = 0; i < nb; i++) {
            if (model.getPioche().getNbCarte() == 0) {
                model.getPioche().resetPioche();
            }
            Zone drownZ = model.getPioche().draw();
            drownZ.drown();
            nbInondation--;
            Boolean escape = false;
            if (drownZ.getWaterLvl() == drownZ.getMaxWaterLvl()) {
                if (gameOverCase(drownZ)) {
                    model.setState(Model.State.LOSE);
                    view.gameOver();
                    break;
                }
                for (Player p : model.getPlayers()) {
                    if (drownZ == p.getPosition()) {
                        p.setState(Player.State.ESCAPE);
                        escape = true;
                        if (escape(drownZ, p)) {
                            escape = true;
                            p.setState(Player.State.ESCAPE);
                        } else {
                            model.setState(Model.State.LOSE);
                            view.gameOver();
                            break;
                        }
                    }
                }
            }
            if (escape) {
                setEscape();
                return;
            }
        }
        nbInondation = 3;
    }
}
