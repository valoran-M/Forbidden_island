package views;

import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Grid
 */
public class ViewGrid extends JPanel {

    public ViewGrid(int height, int width) {
        setLayout(new GridLayout(height, width, 5, 5));
        setBackground(new Color(0, 0, 128));
    }

    public void addElem(JComponent element) {
        add(element);
    }
}