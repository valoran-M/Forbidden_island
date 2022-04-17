package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Model;
import models.Role.Player;
import views.View;

/**
 * ContrPlayer
 */
public class ContrExchange extends Controller implements ActionListener{

    public ContrExchange(Model model, View view) {
        super(model, view);
    }

    public void actionPerformed(ActionEvent e) {
        if (model.getActPlayer().getState() == Player.State.EXCHANGE) {
            model.getActPlayer().setState(Player.State.MOVING);
        } else {
            model.getActPlayer().setState(Player.State.EXCHANGE);
        }
        view.repaint();
    }
}