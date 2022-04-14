package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Model;

/**
 * ContrEndTurn
 */
public class ContrEndTurn extends Controller implements ActionListener{

    public ContrEndTurn(Model model) {
        super(model);
    }

    public void actionPerformed(ActionEvent e) {
        model.nextPlayer();
    }
}