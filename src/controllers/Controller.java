package controllers;

import java.util.ArrayList;

import models.Players;
import views.ViewSetup;

/**
 * Controllers
 */
public class Controller {
    private ArrayList<Players> players;
    private ViewSetup win;

    public Controller() {
        win = new ViewSetup();
    }

    public void setup() {
        win.setUpMenu(this);

        win.drawWin();

        while (!win.isSetup()) {
        }

        initPlayer(win.getNames());

    }

    public void start() {
        win.drawWin();
    }

    /**
     * @param players
     */
    public void initPlayer(ArrayList<String> names) {
        int i = 1;
        for (String name : names) {
            if (name == "") {
                players.add(new Players("Player " + i));
            } else {
                players.add(new Players(name));
            }
            i++;
        }
    }
}