package sk.uniza.fri.game.weapons.player;

public enum WeaponType {
    SHOTGUN("assets/weapons/Shotgun small.png", "assets/weapons/Shotgun small reversed.png", 10, 15),
    MAGIC_WAND("assets/weapons/Magic wand small.png", "assets/weapons/Magic wand small reversed.png", 5, 10);

    private String imagePath;
    private String reverseImagePath;
    private int damage;
    private int fireRate;

    WeaponType(String imagePath, String reverseImagePath, int damage, int fireRate) {
        this.imagePath = imagePath;
        this.reverseImagePath = reverseImagePath;
        this.damage = damage;
        this.fireRate = fireRate;
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

    public int getFireRate() {
        return this.fireRate;
    }
}
