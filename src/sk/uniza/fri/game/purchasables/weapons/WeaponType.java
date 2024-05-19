package sk.uniza.fri.game.purchasables.weapons;

/**
 * Enum that represents the type of weapon.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public enum WeaponType {
    SHOTGUN("assets/weapons/Shotgun small.png", "assets/weapons/Shotgun small reversed.png", 10, 20, "assets/weapons/Shotgun projectiles", 20, 20, 5),
    MAGIC_WAND("assets/weapons/Magic wand small.png", "assets/weapons/Magic wand small reversed.png", 3, 5, "assets/weapons/MagicWand projectiles", 20, 20, 5),
    AK47("assets/weapons/AK47 small.png", "assets/weapons/AK47 small reversed.png", 2, 4, "assets/weapons/AK47 projectiles", 20, 20, 7),
    ROCKET_LAUNCHER("assets/weapons/RocketLauncher small.png", "assets/weapons/RocketLauncher small reversed.png", 50, 40, "assets/weapons/RocketLauncher projectiles", 20, 20, 3);

    private String imagePath;
    private String reverseImagePath;
    private int damage;
    private int fireRate;
    private String projectileImageDirectory;
    private int projectileSpeed;
    private int projectileWidth;
    private int projectileHeight;

    /**
     * Constructor for the WeaponType enum.
     *
     * @param imagePath - String value representing the path to the image of the weapon.
     * @param reverseImagePath - String value representing the path to the image of the weapon when it is reversed.
     * @param damage - Integer value representing the damage of the weapon.
     * @param fireRate - Integer value representing the fire rate of the weapon.
     * @param projectileImageDirectory - String value representing the directory path of the projectile images.
     * @param projectileWidth - Integer value representing the width of the projectile image.
     * @param projectileHeight - Integer value representing the height of the projectile image.
     * @param projectileSpeed - Integer value representing the speed of the projectile.
     */
    WeaponType(String imagePath, String reverseImagePath, int damage, int fireRate, String projectileImageDirectory, int projectileWidth, int projectileHeight, int projectileSpeed) {
        this.imagePath = imagePath;
        this.reverseImagePath = reverseImagePath;
        this.damage = damage;
        this.fireRate = fireRate;
        this.projectileImageDirectory = projectileImageDirectory;
        this.projectileSpeed = projectileSpeed;
        this.projectileWidth = projectileWidth;
        this.projectileHeight = projectileHeight;
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

    public int getProjectileWidth() {
        return this.projectileWidth;
    }

    public int getProjectileHeight() {
        return this.projectileHeight;
    }
}
