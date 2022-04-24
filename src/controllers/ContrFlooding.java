package controllers;

import java.awt.Point;

import java.util.ArrayList;

import models.Model;
import models.Zone;
import models.roles.Player;
import views.View;

/**
 * class for case flooding
 */
public class ContrFlooding extends Controller {
    public int nbInondation;
    private Player escape;

    public ContrFlooding(Model model, View view) {
        super(model, view);
        nbInondation = model.getDelugeLvl().innondationRate();
        this.escape = null;
    }

    /**
     * Geter for escape player
     * 
     * @return Player
     */
    public Player getEscape() {
        return this.escape;
    }

    /**
     * Put a pawn that is on a flooded square in escape
     */
    public void setEscape() {
        Player escape = null;
        for (Player player : model.getPlayers()) {
            if (player.getState() == Player.State.ESCAPE) {
                escape = player;
            }
        }
        this.escape = escape;
    }

    /**
     * To know if a player can escape
     * 
     * @param zone
     * @param player
     * @return Boolean
     */
    private Boolean escape(Zone zone, Player player) {
        ArrayList<Point> action = player.neigboursMove(this.model);

        return !action.isEmpty();
    }

    /**
     * Check if the square that has just been flooded is a temple or a helicopter
     * 
     * @param Zone
     * @return Boolean
     */
    private Boolean gameOverCase(Zone zone) {
        return model.getTemple().contains(zone) ||
                model.getHeliZone() == zone;
    }

    /**
     * flooding case
     */
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
