package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

import models.Model;
import views.View;

/**
 * ContrSetup
 */
public class ContrSetup extends Controller implements ActionListener {

    public ContrSetup(Model model, View view) {
        super(model, view);
    }

    public void actionPerformed(ActionEvent e) {
        ArrayList<String> names = view.getViewSetup().getNamePlayer();
        model.setPlayer(names);
        view.start();
    }
}