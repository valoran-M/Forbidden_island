package controllers;

import models.Model;
import views.View;

/**
 * 1bstract class for controllers
 */
public abstract class Controller {
    protected Model model;
    protected View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }
}