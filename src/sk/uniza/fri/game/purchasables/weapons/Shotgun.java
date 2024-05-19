package sk.uniza.fri.game.purchasables.weapons;

import sk.uniza.fri.game.map.BlockType;
import sk.uniza.fri.game.purchasables.weapons.projectiles.DirectProjectile;

/**
 * Represents the Shotgun weapon.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class Shotgun extends Weapon {

    public Shotgun() {
        super(WeaponType.SHOTGUN, WeaponType.SHOTGUN.getDamage(), WeaponType.SHOTGUN.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        DirectProjectile directProjectile = new DirectProjectile(this.getImage().getX(), this.getImage().getY(), 10, WeaponType.SHOTGUN.getProjectileImageDirectory(), WeaponType.AK47.getProjectileWidth(), WeaponType.AK47.getProjectileHeight(), this, WeaponType.MAGIC_WAND.getProjectileSpeed());
        this.addBullet(directProjectile);
    }

    @Override
    public String getName() {
        return "Shotgun";
    }

    @Override
    public String getDescription() {
        return "Higher damage but slower fire rate";
    }

    @Override
    public int getPrice() {
        return 50;
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.STONE;
    }
}