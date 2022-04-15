package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Model;
import models.Player;
import views.View;

/**
 * ContrEndTurn
 */
public class ContrEndTurn extends ContrEscape implements ActionListener {
    private View view;

    public ContrEndTurn(Model model, View view) {
        super(model);
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        if (model.getNbInondation() != 3) {
            if (flooding(this.model.getNbInondation())) {
                view.gameOver();
            }
        } else if (model.getEscape() == null) {
            model.nextPlayer();
            model.getActPlayer().setState(Player.State.MOVING);
            if (flooding(3)) {
                view.gameOver();
            }
            view.repaint();
        }
    }
}