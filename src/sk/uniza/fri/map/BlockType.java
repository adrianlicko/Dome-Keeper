package sk.uniza.fri.map;

public enum BlockType {
    STONE("assets/Podzemie/Stone block.png", 50, "abc", 2),
    GOLD("assets/Podzemie/Gold block.png", 100, "abc", 2),
    DIAMOND("assets/Podzemie/Diamond block.png", 200, "abc", 2);

    private String imagePath;
    private int health;
    private String coinImagePath;
    private int coinValue;

    BlockType(String imagePath, int health, String coinImagePath, int coinValue) {
        this.imagePath = imagePath;
        this.health = health;
        this.coinImagePath = coinImagePath;
        this.coinValue = coinValue;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public int getHealth() {
        return this.health;
    }

    public String getCoinImagePath() {
        return this.coinImagePath;
    }

    public int getCoinValue() {
        return this.coinValue;
    }
}
