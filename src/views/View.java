package views;

import java.awt.Color;

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

    public JPanel elements;

    final int height = 800;
    final int width = 1000;

    public View(Model m) {
        super("Players Selection");
        this.m = m;
        setSize(500, 400);
        this.setup = new ViewSetup(this.m, this);
        this.grid = new ViewGrid(this.m);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setup() {
        add(this.setup);
    }

    public void start() {
        getContentPane().removeAll();
        setSize(width, height);
        elements = new JPanel();
        elements.setBackground(new Color(55, 55, 55));

        add(elements);

        JButton next = new JButton("End of turn");
        elements.add(this.grid);
        elements.add(next);
        
        repaint();
    }
}