package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.*;
import models.Model;

/**
 * View
 */
public class View extends JFrame {
    private Model model;

    private ViewSetup setup;
    public ViewGrid grid;
    public ViewPlayer player;
    public ViewTreasure treasure;

    private JPanel buttons;
    private JPanel gameOverButtons;

    private ContrEndTurn contrEndTurn;
    private ContrFlooding contrFlooding;
    private ContrExchange contrExchange;
    private ContrDry contrDig;
    private ContrSearch contrSearch;

    private JPanel elements;
    public Color background;

    public int height;
    public int width;

    public View(Model m) {
        super();
        this.model = m;
        setSize(500, 400);
        this.background = new Color(55, 55, 55);

        this.elements = new JPanel();
        this.elements.setBackground(background);

        this.contrFlooding = new ContrFlooding(this.model, this);
        this.contrEndTurn = new ContrEndTurn(this.model, this, this.contrFlooding);
        this.contrDig = new ContrDry(this.model, this);
        this.contrExchange = new ContrExchange(this.model, this);
        this.contrSearch = new ContrSearch(model, this);

        this.setup = new ViewSetup(this.model, this);
        this.grid = new ViewGrid(this.model, this, this.contrFlooding);
        this.player = new ViewPlayer(this.model, this, this.contrExchange);
        this.treasure = new ViewTreasure(this.model, this, this.grid);

        this.buttons = new JPanel();
        this.buttons.setPreferredSize(new Dimension(this.grid.widthJpanel + this.player.width + 30, 100));
        this.buttons.setBackground(background);
        JButton search = new JButton("Search");
        search.setPreferredSize(new Dimension(this.grid.widthJpanel / 3, 50));
        search.addActionListener(this.contrSearch);
        JButton dig = new JButton("Dry up");
        dig.setPreferredSize(new Dimension(this.grid.widthJpanel / 3, 50));
        dig.addActionListener(this.contrDig);
        JButton exchange = new JButton("Exchange");
        exchange.setPreferredSize(new Dimension(this.grid.widthJpanel / 3, 50));
        exchange.addActionListener(this.contrExchange);
        JButton next = new JButton("End of turn");
        next.setPreferredSize(new Dimension(this.player.width, 50));
        next.addActionListener(this.contrEndTurn);
        buttons.add(search);
        buttons.add(dig);
        buttons.add(exchange);
        buttons.add(next);

        this.width = this.grid.widthJpanel + this.player.width + 100;
        this.height = this.grid.heightJpanel + 300;

        treasure.setPreferredSize(new Dimension(this.grid.widthJpanel + this.player.width, 150));

        elements.add(this.grid);
        elements.add(this.player);
        elements.add(this.buttons);
        elements.add(this.treasure);

        this.gameOverButtons = new JPanel();
        this.gameOverButtons.setPreferredSize(new Dimension(this.grid.widthJpanel + this.player.width + 30, 100));
        this.gameOverButtons.setBackground(background);

        JButton exit = new JButton("Exit");
        exit.setPreferredSize(new Dimension((this.grid.widthJpanel + this.player.width) / 2, 50));
        exit.addActionListener(e -> {
            this.dispose();
        });
        JButton restart = new JButton("Restart");
        restart.setPreferredSize(new Dimension((this.grid.widthJpanel + this.player.width) / 2, 50));
        restart.addActionListener(e -> {
            this.model.reset();
            this.elements.remove(this.gameOverButtons);
            this.elements.add(this.buttons);
            this.elements.add(this.treasure);
            setup();
        });
        gameOverButtons.add(restart);
        gameOverButtons.add(exit);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setup() {
        setSize(500, 400);
        getContentPane().removeAll();
        add(this.setup);
        repaint();
    }

    public void start() {
        this.model.setState(Model.State.RUNNING);
        getContentPane().removeAll();
        setBackground(background);

        this.grid.initPawn();
        setSize(this.width, this.height);

        add(this.elements);

        this.repaint();
    }

    public void gameOver() {
        elements.remove(this.buttons);
        elements.remove(this.treasure);
        elements.add(this.gameOverButtons);
        elements.add(this.treasure);

        elements.updateUI();
        this.repaint();
    }

    public ViewSetup getViewSetup() {
        return this.setup;
    }
}