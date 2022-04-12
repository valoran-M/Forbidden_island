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
    private View view;

    public ContrSetup(Model model, View view) {
        super(model);
        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        ArrayList<String> names = view.setup.getNamePlayer();
        view.start();
    }
}