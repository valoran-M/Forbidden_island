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

    private int height;
    private int width;

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
        elements = new JPanel();
        elements.setBackground(new Color(55, 55, 55));

        this.grid.initPawn();

        comment = new JTextArea();
        comment.setFont(comment.getFont().deriveFont(20f));
        comment.setPreferredSize(new Dimension(300, this.grid.heightJpanel - 40));
        comment.setBackground(new Color(100, 100, 100));
        changeText();

        JButton next = new JButton("End of turn");
        next.setPreferredSize(new Dimension(this.grid.widthJpanel / 3, 50));
        next.addActionListener(contrEndTurn);
        JButton dig = new JButton("Dig");
        dig.setPreferredSize(new Dimension(this.grid.widthJpanel / 3, 50));
        JButton pick = new JButton("Pick");
        pick.setPreferredSize(new Dimension(this.grid.widthJpanel / 3, 50));

        elements.add(this.grid);
        elements.add(this.comment);
        elements.add(next);
        elements.add(dig);
        elements.add(pick);
        add(elements);

        this.width = this.grid.widthJpanel + this.comment.getPreferredSize().width + 50;
        this.height = this.grid.heightJpanel + 300;
        setSize(width, height);
        this.grid.repaint();
        this.repaint();
    }

    public void changeText() {
        String text = model.getActPlayer().getComment();
        this.comment.setText(text);
        this.comment.repaint();
    }

    public ViewSetup getViewSetup() {
        return this.setup;
    }
}