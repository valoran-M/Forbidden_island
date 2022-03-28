package controllers;

import java.util.ArrayList;

import models.Ile;
import models.Players;
import views.Window;

/**
 * Controllers
 */
public class Controller {
    private ArrayList<Players> players;
    private Window win;
    private Ile ile;

    public Controller() {
        win = new Window();
    }

    public void setup() {
        win.setUpMenu(this);

        win.drawWin();

        while (!win.isSetup()) {
        }

    }

    public void start() {
        ile = new Ile(10);
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