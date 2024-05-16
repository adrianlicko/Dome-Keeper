package sk.uniza.fri.game.purchasable.weapons;

import sk.uniza.fri.game.purchasable.weapons.projectiles.DirectProjectile;

public class AK47 extends Weapon {
    public AK47() {
        super(WeaponType.AK47, WeaponType.AK47.getDamage(), WeaponType.AK47.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        DirectProjectile directProjectile = new DirectProjectile(this.getImage().getX(), this.getImage().getY(), 10, WeaponType.AK47.getProjectileImageDirectory(), this, WeaponType.AK47.getProjectileSpeed());
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
}
