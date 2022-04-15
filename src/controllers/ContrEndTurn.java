package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Model;
import models.Player;
import views.View;

/**
 * ContrEndTurn
 */
public class ContrEndTurn extends Controller implements ActionListener{
    private View view;

    public ContrEndTurn(Model model, View view) {
        super(model);
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        model.nextPlayer();
        model.getActPlayer().setState(Player.State.MOVING);
        flooding();
        view.repaint();
    }

    public void flooding() {
        for(int i = 0; i < 3; i++){
            if(model.getPioche().getNbCarte() == 0){
                model.getPioche().resetPioche();
            }
            model.getPioche().draw().drown();
        }
    }
}