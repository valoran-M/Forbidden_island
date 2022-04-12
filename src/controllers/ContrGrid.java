package controllers;

import models.Model;

/**
 * ContrGrid
 */
public class ContrGrid extends Controller {

    public ContrGrid(Model model) {
        super(model);
    }

    public void click(int x, int y) {
        System.out.println(x + " " + y);
    }
}