package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Model;
import models.Role.Player;
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
            model.nextPlayer();
            model.getActPlayer().setState(Player.State.MOVING);
            model.getActPlayer().setAction(3);
            contrEscape.flooding(3);
            view.repaint();
        }
    }
}