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

    public JPanel elements;

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
        elements = new JPanel();
        elements.setLayout(null);
        add(elements);

        JButton next = new JButton("End of turn");
        next.setLayout(null);
        next.setBounds(300, 300, 100, 50);
        elements.add(this.grid);
        elements.add(next);
        repaint();
    }
}