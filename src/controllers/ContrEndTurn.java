package controllers;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import models.Model;
import models.roles.Player;
import models.roles.Player.State;
import models.Card;
import views.View;


/**
 * ContrEndTurn
 */
public class ContrEndTurn extends Controller implements ActionListener {
    private ContrFlooding contrEscape;

    public ContrEndTurn(Model model, View view, ContrFlooding contrEscape) {
        super(model, view);
        this.contrEscape = contrEscape;
    }

    public void actionPerformed(ActionEvent e) {
        if (contrEscape.getEscape() == null) {
            model.getActPlayer().powerUp();

            for(int i = 0; i < 2; i++){
                Card actualCard = model.getPiocheCard().pick();
                if(actualCard.equals(Card.DELUGE)){
                    model.getDelugeLvl().incrementLvl();
                    model.getPiocheWater().addDefausse();    
                    model.getPiocheCard().sendToDefausse(actualCard);
                } else if(model.getActPlayer().getAllCards().size() == 5) {
                    model.getActPlayer().setState(State.THROW);
                } else {
                    model.getActPlayer().addcard(actualCard);
                }
            }

            model.nextPlayer();
            model.getActPlayer().setState(Player.State.MOVING);
            model.getActPlayer().setAction(3);
            contrEscape.flooding();
            view.repaint();
        }
    }
}