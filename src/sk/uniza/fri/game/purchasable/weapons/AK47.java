package sk.uniza.fri.game.purchasable.weapons;

import sk.uniza.fri.game.map.BlockType;
import sk.uniza.fri.game.purchasable.weapons.projectiles.DirectProjectile;

/**
 * Represents the AK47 weapon.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class AK47 extends Weapon {
    public AK47() {
        super(WeaponType.AK47, WeaponType.AK47.getDamage(), WeaponType.AK47.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        DirectProjectile directProjectile = new DirectProjectile(this.getImage().getX(), this.getImage().getY(), 10, WeaponType.AK47.getProjectileImageDirectory(), WeaponType.AK47.getProjectileWidth(), WeaponType.AK47.getProjectileHeight(), this, WeaponType.AK47.getProjectileSpeed());
        this.addBullet(directProjectile);
    }

    @Override
    public String getName() {
        return "AK47";
    }

    @Override
    public String getDescription() {
        return "Lowest damage, but fastest fire rate";
    }

    @Override
    public int getPrice() {
        return 5;
    }

    @Override
    public BlockType getBlockType() {
        return BlockType.GOLD;
    }
}