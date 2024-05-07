package sk.uniza.fri;

import sk.uniza.fri.game.Game;

public class Main {
    public static void main(String[] args) {
        Game.getInstance();
        var manager = Game.getInstance().getManager();
        manager.manageObject(Game.getInstance());
    }
}
