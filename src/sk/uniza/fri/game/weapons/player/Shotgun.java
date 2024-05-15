package sk.uniza.fri.game.weapons.player;

import sk.uniza.fri.game.weapons.DirectProjectile;
import sk.uniza.fri.game.weapons.Weapon;

public class Shotgun extends Weapon implements Purchasable {

    public Shotgun() {
        super(WeaponType.SHOTGUN, WeaponType.SHOTGUN.getDamage(), WeaponType.SHOTGUN.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        DirectProjectile directProjectile = new DirectProjectile(this.getImage().getX(), this.getImage().getY(), 10, "assets/weapons/bullet", this, 5);
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