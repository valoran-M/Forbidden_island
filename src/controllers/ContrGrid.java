package controllers;

import models.Model;
import views.ViewGrid;

/**
 * ContrGrid
 */
public class ContrGrid extends Controller {
    ViewGrid view;

    public ContrGrid(Model model, ViewGrid view) {
        super(model);
        this.view = view;
    }

    public void click(int x, int y) {
        model.getActPlayer().move(x, y);
        view.repaint();
    }
}