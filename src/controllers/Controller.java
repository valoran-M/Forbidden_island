package controllers;

import models.Model;

/**
 * Controllers
 */
public abstract class Controller {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}