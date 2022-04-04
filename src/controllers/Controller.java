package controllers;

import java.util.ArrayList;
import java.util.Random;

import models.Isle;
import models.Player;
import views.ViewGame;
import views.ViewSetup;

/**
 * Controllers
 */
public class Controller {
    final int size = 6;
    private ArrayList<Player> players;
    private ViewSetup setup;
    private ViewGame game;
    private Isle isle;
    private Random rand;

    public Controller() {
        players = new ArrayList<Player>();
        setup = new ViewSetup();
        game = new ViewGame();
        isle = new Isle(size);
        rand = new Random();

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
            int y = rand.nextInt(isle.getGridSize());
            ArrayList<Integer> Ax = isle.getCoordLine(y);
            int x = rand.nextInt(Ax.size());
            players.add(new Player(name, isle.getZone(x, y)));
        }
    }
}