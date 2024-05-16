package sk.uniza.fri.game.purchasable.weapons;

import sk.uniza.fri.game.purchasable.Purchasable;
import sk.uniza.fri.game.purchasable.weapons.projectiles.DirectProjectile;

public class Shotgun extends Weapon {

    public Shotgun() {
        super(WeaponType.SHOTGUN, WeaponType.SHOTGUN.getDamage(), WeaponType.SHOTGUN.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        DirectProjectile directProjectile = new DirectProjectile(this.getImage().getX(), this.getImage().getY(), 10, WeaponType.SHOTGUN.getProjectileImageDirectory(), this, WeaponType.MAGIC_WAND.getProjectileSpeed());
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
}