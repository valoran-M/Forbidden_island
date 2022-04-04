import models.Model;
import views.View;

public class Game {
    public static void main(String[] args) {
        Model m = new Model(6);
        View v = new View(m);
        v.setup();
    }
}