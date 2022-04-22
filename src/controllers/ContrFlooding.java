package controllers;

import java.awt.Point;

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
        nbInondation = model.getDelugeLvl().innondationRate();
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
        ArrayList<Point> action = player.neigboursMove(this.model);

        return !action.isEmpty();
    }

    private Boolean gameOverCase(Zone zone) {
        return model.getTemple().contains(zone) ||
                model.getHeliZone() == zone;
    }

    public void flooding() {
        for (; nbInondation > 0; nbInondation--) {
            Zone drownZ = model.getPiocheWater().pick();
            drownZ.drown();
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
        nbInondation = model.getDelugeLvl().innondationRate();
    }
}
