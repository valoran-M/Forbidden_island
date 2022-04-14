package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controllers.ContrEndTurn;
import models.Model;

/**
 * View
 */
public class View extends JFrame {
    private Model model;

    private ViewSetup setup;
    private ViewGrid grid;

    private ContrEndTurn contrEndTurn;

    private JPanel elements;
    private JTextArea comment;

    final int height = 800;
    final int width = 1000;

    public View(Model m) {
        super("Players Selection");
        this.model = m;
        setSize(500, 400);
        this.setup = new ViewSetup(this.model, this);
        this.grid = new ViewGrid(this.model);

        contrEndTurn = new ContrEndTurn(model);

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

        comment = new JTextArea();
        comment.setPreferredSize(new Dimension(300, this.grid.sizeJpanel - 40));
        comment.setBackground(new Color(100, 100, 100));
        comment.setText("Salut");

        JButton next = new JButton("End of turn");
        next.setPreferredSize(new Dimension(this.grid.sizeJpanel / 3, 50));
        next.addActionListener(contrEndTurn);
        JButton dig = new JButton("Dig");
        dig.setPreferredSize(new Dimension(this.grid.sizeJpanel / 3, 50));
        JButton pick = new JButton("Pick");
        pick.setPreferredSize(new Dimension(this.grid.sizeJpanel / 3, 50));

        elements.add(this.grid);
        elements.add(this.comment);
        elements.add(next);
        elements.add(dig);
        elements.add(pick);
        
        repaint();
    }

    public void changeText(String text) {
        this.comment.setText(text);
        this.comment.repaint();
    }

    public ViewSetup getViewSetup() {
        return this.setup;
    }
}