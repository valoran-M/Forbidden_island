package views;

import javax.swing.JFrame;

import models.Model;

/**
 * View
 */
public class View extends JFrame {
    private Model m;

    public ViewSetup setup;

    public View(Model m) {
		super("Players Selection");
        this.m = m;
        setSize(500, 400);
        this.setup = new ViewSetup(this.m, this);

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setup(){
        add(setup);
        setVisible(true);
    }

    public void start(){
        getContentPane().removeAll();
        repaint();
    }
}