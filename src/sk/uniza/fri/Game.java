package sk.uniza.fri;

import fri.shapesge.Manager;
import sk.uniza.fri.map.Map;
import sk.uniza.fri.player.Astronaut;

public class Game {
    public static void startGame() {
        Map.getInstance().createBlocks();
        var astronaut = new Astronaut(450, 250);
        var manager = new Manager();
        manager.manageObject(astronaut);
    }
}
