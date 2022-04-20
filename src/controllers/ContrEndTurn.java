package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Model;
import models.roles.Player;
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
            /*
             * for(int i = 0; i < 2; i++){
             * model.getActPlayer().addcard(model.getPiocheCard().pick());
             * }
             */
            model.nextPlayer();
            model.getActPlayer().setState(Player.State.MOVING);
            model.getActPlayer().setAction(3);
            contrEscape.flooding();
            view.repaint();
        }
    }
}