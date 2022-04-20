package controllers;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import models.Model;
import models.roles.Player;
import models.Card;
import views.View;

/**
 * ContrEndTurn
 */
public class ContrEndTurn extends Controller implements ActionListener {
    public ContrFlooding contrEscape;
    public ContrPlayer contrPlayer;

    public ContrEndTurn(Model model, View view, ContrFlooding contrEscape) {
        super(model, view);
        this.contrEscape = contrEscape;
    }

    public void nexTurn(){
        model.nextPlayer();
        model.getActPlayer().setState(Player.State.MOVING);
        model.getActPlayer().setAction(3);
        contrEscape.flooding();
    }

    public void actionPerformed(ActionEvent e) {
        if (contrEscape.getEscape() == null) {
            model.getActPlayer().powerUp();

            for (int i = 0; i < 2; i++) {
                Card actualCard = model.getPiocheCard().pick();
                if (actualCard.equals(Card.DELUGE)) {
                    model.getDelugeLvl().incrementLvl();
                    model.getPiocheWater().addDefausse();
                    model.getPiocheCard().sendToDefausse(actualCard);
                } else {
                    model.getActPlayer().addcard(actualCard);
                }
            }
            if (model.getActPlayer().getNbCards() > 5) {
                model.getActPlayer().setState(Player.State.THROW);
                contrPlayer.selectedPlayer = model.getActPlayer();
            } else {
                nexTurn();
            }
            view.repaint();
        }
    }
}