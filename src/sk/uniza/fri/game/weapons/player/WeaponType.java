package sk.uniza.fri.game.weapons.player;

public enum WeaponType {
    SHOTGUN("assets/weapons/Shotgun small.png", "assets/weapons/Shotgun small reversed.png", 10, 20, "assets/weapons/Shotgun projectiles", 5),
    MAGIC_WAND("assets/weapons/Magic wand small.png", "assets/weapons/Magic wand small reversed.png", 3, 5, "assets/weapons/MagicWand projectiles", 5),
    AK47("assets/weapons/AK47 small.png", "assets/weapons/AK47 small reversed.png", 2, 4, "assets/weapons/AK47 projectiles", 7),
    ROCKET_LAUNCHER("assets/weapons/RocketLauncher small.png", "assets/weapons/RocketLauncher small reversed.png", 50, 40, "assets/weapons/RocketLauncher projectiles", 3);

    private String imagePath;
    private String reverseImagePath;
    private int damage;
    private int fireRate;
    private String projectileImageDirectory;
    private int projectileSpeed;

    WeaponType(String imagePath, String reverseImagePath, int damage, int fireRate, String projectileImageDirectory, int projectileSpeed) {
        this.imagePath = imagePath;
        this.reverseImagePath = reverseImagePath;
        this.damage = damage;
        this.fireRate = fireRate;
        this.projectileImageDirectory = projectileImageDirectory;
        this.projectileSpeed = projectileSpeed;
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

    public String getProjectileImageDirectory() {
        return this.projectileImageDirectory;
    }

    public int getProjectileSpeed() {
        return this.projectileSpeed;
    }
}
