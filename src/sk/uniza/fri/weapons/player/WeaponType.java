package sk.uniza.fri.weapons.player;

public enum WeaponType {
    SHOTGUN("assets/weapons/Shotgun small.png", "assets/weapons/Shotgun small reversed.png", 10);

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
