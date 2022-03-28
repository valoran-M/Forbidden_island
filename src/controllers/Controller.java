package controllers;

import java.util.ArrayList;
import java.util.Observer;

import models.Isle;
import models.Players;
import views.ViewGame;
import views.ViewSetup;

/**
 * Controllers
 */
public class Controller{
    private ArrayList<Players> players;
    private ViewSetup setup;
    private ViewGame game;
    private Isle isle;

    public Controller() {
        players = new ArrayList<Players>();
        setup = new ViewSetup();
        game = new ViewGame();
        isle = new Isle(6);
    }

    public void setup() {
        setup.setUpMenu(this);
        setup.drawWin();
    }

    public void run() {

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