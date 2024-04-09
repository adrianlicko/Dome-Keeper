package sk.uniza.fri.map;

public enum BlockType {
    STONE("assets/Podzemie/Stone block.png", 50);

    private String imagePath;
    private int health;

    BlockType(String imagePath, int health) {
        this.imagePath = imagePath;
        this.health = health;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public int getHealth() {
        return this.health;
    }
}
