import java.io.IOException;

import models.Model;
import views.View;

public class Game {
    public static void main(String[] args) throws IOException {
        Model m;
        if (args.length == 0) {
            m = new Model("map/default.map");
        }else{
            m = new Model(args[0]);
        }
        View v = new View(m);
        v.setup();
    }
}