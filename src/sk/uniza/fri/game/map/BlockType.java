package sk.uniza.fri.game.map;

/**
 * Enum that represents the type of the block.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public enum BlockType {
    STONE("assets/underground/Stone block.png", 50, "assets/underground/Stone nugget icon.png", 2),
    GOLD("assets/underground/Gold block.png", 100, "assets/underground/Gold nugget icon.png", 2),
    DIAMOND("assets/underground/Diamond block.png", 200, "assets/underground/Diamond nugget icon.png", 2);

    private final String imagePath;
    private final int health;
    private final String coinImagePath;
    private final int coinValue;

    /**
     * Constructor for the BlockType enum.
     *
     * @param imagePath - String value representing the path to the image of the block.
     * @param health - Integer value representing the health of the block.
     * @param coinImagePath - String value representing the path to the image of the coin.
     * @param coinValue - Integer value representing the value of the coin.
     */
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
