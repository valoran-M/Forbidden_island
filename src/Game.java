import models.Ile;
import views.Window;

public class Game {
    public static void main(String[] args) {
        Window win = new Window();
        Ile ile = new Ile(6);
        win.addElem(ile);
        win.drawWin();
    }
}