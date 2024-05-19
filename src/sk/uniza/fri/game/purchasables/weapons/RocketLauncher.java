package sk.uniza.fri.game.purchasables.weapons;

import sk.uniza.fri.game.map.BlockType;
import sk.uniza.fri.game.purchasables.weapons.projectiles.HomingProjectile;

/**
 * Represents the Rocket Launcher weapon.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class RocketLauncher extends Weapon {
    public RocketLauncher() {
        super(WeaponType.ROCKET_LAUNCHER, WeaponType.ROCKET_LAUNCHER.getDamage(), WeaponType.ROCKET_LAUNCHER.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        HomingProjectile homingProjectile = new HomingProjectile(this.getImage().getX(), this.getImage().getY(), WeaponType.ROCKET_LAUNCHER.getDamage(), WeaponType.ROCKET_LAUNCHER.getProjectileImageDirectory(), WeaponType.ROCKET_LAUNCHER.getProjectileWidth(), WeaponType.ROCKET_LAUNCHER.getProjectileHeight(), this, WeaponType.ROCKET_LAUNCHER.getProjectileSpeed());
        this.addBullet(homingProjectile);
    }

    @Override
    public String getName() {
        return "Rocket Launcher";
    }

    @Override
    public String getDescription() {
        return "Highest damage, but slowest fire rate";
    }

    @Override
    public int getPrice() {
        return 10;
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.GOLD;
    }
}
