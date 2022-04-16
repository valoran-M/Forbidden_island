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

    private ContrEndTurn contrEndTurn;
    private ContrFlooding contrFlooding;
    private ContrDig contrDig;
    private ContrExchange contrExchange;

    private JPanel elements;
    public Color background;

    private int height;
    private int width;

    public View(Model m) {
        super();
        this.model = m;
        setSize(500, 400);

        this.contrFlooding = new ContrFlooding(this.model, this);
        this.contrEndTurn = new ContrEndTurn(this.model, this, this.contrFlooding);
        this.contrDig = new ContrDig(this.model, this);
        this.contrExchange = new ContrExchange(this.model, this);
        this.background = new Color(55, 55, 55);

        this.setup = new ViewSetup(this.model, this);
        this.grid = new ViewGrid(this.model, this, this.contrFlooding);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setup() {
        add(this.setup);
    }

    public void start() {
        getContentPane().removeAll();
        setBackground(background);
        elements = new JPanel();
        elements.setBackground(background);

        this.grid.initPawn();
        this.player = new ViewPlayer(this.model, this, this.contrExchange);

        this.width = this.grid.widthJpanel + +this.player.width + 100;
        this.height = this.grid.heightJpanel + 300;
        setSize(width, height);

        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(this.grid.widthJpanel + this.player.width + 30, 100));
        buttons.setBackground(background);

        JButton search = new JButton("Search");
        search.setPreferredSize(new Dimension(this.grid.widthJpanel / 3, 50));
        search.addActionListener(contrDig);
        JButton dig = new JButton("Dry up");
        dig.setPreferredSize(new Dimension(this.grid.widthJpanel / 3, 50));
        dig.addActionListener(contrDig);
        JButton exchange = new JButton("Exchange");
        exchange.setPreferredSize(new Dimension(this.grid.widthJpanel / 3, 50));
        exchange.addActionListener(contrExchange);
        JButton next = new JButton("End of turn");
        next.setPreferredSize(new Dimension(this.player.width, 50));
        next.addActionListener(contrEndTurn);

        buttons.add(search);
        buttons.add(dig);
        buttons.add(exchange);
        buttons.add(next);

        elements.add(this.grid);
        elements.add(this.player);
        elements.add(buttons);

        add(elements);

        this.repaint();
    }

    public void gameOver(){
        setVisible(false);
        dispose();
    }

    public ViewSetup getViewSetup() {
        return this.setup;
    }
}