package sk.uniza.fri.game.weapons.player;

public enum WeaponType {
    SHOTGUN("assets/weapons/Shotgun small.png", "assets/weapons/Shotgun small reversed.png", 10),
    MAGIC_WAND("assets/weapons/Magic wand small.png", "assets/weapons/Magic wand small reversed.png", 5);

    private String imagePath;
    private String reverseImagePath;
    private int damage;

    WeaponType(String imagePath, String reverseImagePath, int damage) {
        this.imagePath = imagePath;
        this.reverseImagePath = reverseImagePath;
        this.damage = damage;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getReverseImagePath() {
        return this.reverseImagePath;
    }

    public int getDamage() {
        return this.damage;
    }
}
