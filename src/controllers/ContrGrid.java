package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import models.Model;

/**
 * ContrGrid
 */
public class ContrGrid extends Controller implements ActionListener{

    public ContrGrid(Model model) {
        super(model);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}