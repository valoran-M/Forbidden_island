package controllers;

import java.util.ArrayList;

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

        game.addElem(isle);
    }

    public void setup() {
        setup.setUpMenu(this);
        setup.drawWin();
    }

    public void run() {
        game.drawWin();
    }

    /**
     * @param players
     */
    public void initPlayer(ArrayList<String> names) {
        for (String name : names) {
            players.add(new Players(name));
        }
    }
}