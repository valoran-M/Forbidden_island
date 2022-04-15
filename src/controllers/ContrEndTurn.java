package controllers;

import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;

import models.Model;
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
        view.repaint();
    }

    public void flooding(ActionEvent e) {
        for(int i = 0; i < 3; i++){
            model.getIsland().getRandomCase().drown();
        }
    }
}