package controllers;

import models.Ile;
import views.Window;

/**
 * Controllers
 */
public class Controller {
    private Window win;
    private Ile ile;

    public Controller() {
        win = new Window();
    }

    public void setup(){
        win.setUpMenu();

        win.drawWin();
    }

    public void start() {
        ile = new Ile(10);
        win.drawWin();
    }
}