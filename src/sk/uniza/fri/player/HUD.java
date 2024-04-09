package sk.uniza.fri.player;

import fri.shapesge.Rectangle;
import sk.uniza.fri.map.Map;

public class HUD {
    private static HUD instance;
    private Rectangle hudBackground;

    private HUD() {
        this.hudBackground = new Rectangle(0, 715);
        this.hudBackground.changeSize(1008, 35);
        this.hudBackground.changeColor("black");
        this.hudBackground.makeVisible();
    }

    public static HUD getInstance() {
        if (instance == null) {
            instance = new HUD();
        }
        return instance;
    }

}
