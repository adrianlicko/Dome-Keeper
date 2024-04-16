package sk.uniza.fri;

import fri.shapesge.Manager;
import sk.uniza.fri.map.GameMap;
import sk.uniza.fri.player.Astronaut;
import sk.uniza.fri.player.Dome;
import sk.uniza.fri.player.HUD;

public class Game {
    public static void startGame() {
        GameMap.getInstance().createBlocks();


        Astronaut.getInstance();
        var manager = new Manager();
        manager.manageObject(Astronaut.getInstance());
        HUD.getInstance();
        Dome.getInstance();
    }
}
