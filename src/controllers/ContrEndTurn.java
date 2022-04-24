package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Model;
import models.roles.Player;
import models.Card;
import views.View;

/**
 * class for end turn buttons
 */
public class ContrEndTurn extends Controller implements ActionListener {
    public ContrFlooding contrEscape;
    public ContrPlayer contrPlayer;
    public int maxCard;

    public ContrEndTurn(Model model, View view, ContrFlooding contrEscape) {
        super(model, view);
        this.contrEscape = contrEscape;
        this.maxCard = 5;
    }

    /**
     * update the model
     */
    public void nexTurn() {
        model.nextPlayer();
        model.getActPlayer().setState(Player.State.MOVING);
        model.getActPlayer().setAction(3);
        contrEscape.flooding();
    }

    /**
     * Manages all events after 
     * the use of the 3 actions put in the rule
     */
    public void actionPerformed(ActionEvent e) {
        if (contrEscape.getEscape() == null) {
            model.getActPlayer().powerUp();
            if (model.getActPlayer().getState() != Player.State.THROW) {
                for (int i = 0; i < 2; i++) {
                    Card actualCard = model.getPiocheCard().pick();
                    if (actualCard.equals(Card.DELUGE)) {
                        model.getDelugeLvl().incrementLvl();
                        if(model.getDelugeLvl().getLvl() == 9) {
                            model.setState(Model.State.LOSE);
                            view.gameOver();
                        }
                        model.getPiocheWater().addDefausse();
                        model.getPiocheCard().sendToDefausse(actualCard);
                    } else {
                        model.getActPlayer().addcard(actualCard);
                    }
                }
            }
            if (model.getActPlayer().getNbCards() > maxCard) {
                model.getActPlayer().setState(Player.State.THROW);
                contrPlayer.selectedPlayer = model.getActPlayer();
            } else {
                nexTurn();
            }
            view.repaint();
        }
    }
}