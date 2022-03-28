package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class ViewCase extends JButton {
    static private Border lineborder = BorderFactory.createLineBorder(Color.RED, 1);
    
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

    public void setBorder(){
        setBorder(lineborder);
    }

    public void removeBorder(){
        setBorder(null);
    }
}