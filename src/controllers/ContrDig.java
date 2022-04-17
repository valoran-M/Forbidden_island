package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import views.View;
import models.Model;
import models.Role.Player;


/**
 * ContlDig
 */
public class ContrDig extends Controller implements ActionListener {
    private View view;

    public ContrDig(Model model, View view) {
        super(model, view);
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        if (model.getActPlayer().getState() == Player.State.DIGGING) {
            model.getActPlayer().setState(Player.State.MOVING);
        } else {
            model.getActPlayer().setState(Player.State.DIGGING);
        }
        view.repaint();
    }
}