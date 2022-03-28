package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ViewCase extends JPanel {
    public ViewCase(int x, int y, boolean empty) {
        setPreferredSize(new Dimension(x, y));
        if (empty) {
            setBackground(new Color(0, 0, 0, 0));
        } else {
            setBackground(new Color(200, 200, 200));
        }
    }

    public ViewCase(int x, int y) {
        this(x, y, false);
    }
}