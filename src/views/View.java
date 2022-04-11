package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Model;

/**
 * View
 */
public class View extends JFrame {
    private Model m;

    public ViewSetup setup;
    public ViewGrid grid;

    public JPanel game;

    final int height = 600;
    final int width = 800;

    public View(Model m) {
        super("Players Selection");
        this.m = m;
        setSize(500, 400);
        this.setup = new ViewSetup(this.m, this);
        this.grid = new ViewGrid(this.m);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setup() {
        add(this.setup);
        setVisible(true);
    }

    public void start() {
        getContentPane().removeAll();
        setSize(width, height);
        game = new JPanel();
        game.setLayout(null);
        game.setBounds(0, 0, width, height);
        game.add(this.grid);
        add(game);
        repaint();
    }
}