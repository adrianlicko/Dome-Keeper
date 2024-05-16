package sk.uniza.fri.game.weapons.player;

import sk.uniza.fri.game.weapons.HomingProjectile;
import sk.uniza.fri.game.weapons.Weapon;

public class RocketLauncher extends Weapon implements Purchasable {
    public RocketLauncher() {
        super(WeaponType.ROCKET_LAUNCHER, WeaponType.ROCKET_LAUNCHER.getDamage(), WeaponType.ROCKET_LAUNCHER.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        HomingProjectile homingProjectile = new HomingProjectile(this.getImage().getX(), this.getImage().getY(), 10, WeaponType.ROCKET_LAUNCHER.getProjectileImageDirectory(), this, WeaponType.ROCKET_LAUNCHER.getProjectileSpeed());
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
}
