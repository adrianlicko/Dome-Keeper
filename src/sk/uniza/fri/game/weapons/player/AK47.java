package sk.uniza.fri.game.weapons.player;

import sk.uniza.fri.game.weapons.DirectProjectile;
import sk.uniza.fri.game.weapons.Weapon;

public class AK47 extends Weapon implements Purchasable {
    public AK47() {
        super(WeaponType.AK47, WeaponType.AK47.getDamage(), WeaponType.AK47.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        DirectProjectile directProjectile = new DirectProjectile(this.getImage().getX(), this.getImage().getY(), 10, WeaponType.AK47.getProjectileImageDirectory(), this, 7);
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
