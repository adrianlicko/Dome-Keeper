package sk.uniza.fri.game.purchasable.weapons;

import sk.uniza.fri.game.purchasable.Purchasable;
import sk.uniza.fri.game.purchasable.weapons.projectiles.HomingProjectile;

public class MagicWand extends Weapon {
    public MagicWand() {
        super(WeaponType.MAGIC_WAND, WeaponType.MAGIC_WAND.getDamage(), WeaponType.MAGIC_WAND.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        HomingProjectile homingProjectile = new HomingProjectile(this.getImage().getX(), this.getImage().getY(), 10, WeaponType.MAGIC_WAND.getProjectileImageDirectory(), this, WeaponType.MAGIC_WAND.getProjectileSpeed());
        this.addBullet(homingProjectile);
    }

    @Override
    public String getName() {
        return "Magic Wand";
    }

    @Override
    public String getDescription() {
        return "Lower damage but faster fire rate";
    }

    @Override
    public int getPrice() {
        return 20;
    }
}