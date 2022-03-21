package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Case extends JPanel {
    public Case(int x, int y, boolean empty) {
        setPreferredSize(new Dimension(x, y));
        if (empty) {
            setBackground(new Color(0, 0, 0, 0));
        } else {
            setBackground(new Color(200, 200, 200));
        }
    }

    public Case(int x, int y) {
        this(x, y, false);
    }
}