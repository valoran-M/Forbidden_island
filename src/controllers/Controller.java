package controllers;

import models.Model;
import views.View;

/**
 * Controllers
 */
public abstract class Controller {
    protected Model model;
    protected View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }
}