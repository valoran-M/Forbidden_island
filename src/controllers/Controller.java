package controllers;

import models.Model;

/**
 * Controllers
 */
public abstract class Controller {
    protected Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}