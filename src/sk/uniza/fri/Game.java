package sk.uniza.fri;

import fri.shapesge.Manager;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.player.Astronaut;
import sk.uniza.fri.player.HUD;

public class Game {
    public static void startGame() {
        Map.getInstance().createBlocks();
        HUD.getInstance();

        var astronaut = new Astronaut(450, 250);
        var manager = new Manager();
        manager.manageObject(astronaut);
    }
}
