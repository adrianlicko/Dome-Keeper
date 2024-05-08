package sk.uniza.fri.game.weapons.player;

import sk.uniza.fri.game.weapons.DirectProjectile;
import sk.uniza.fri.game.weapons.Weapon;

public class Shotgun extends Weapon {

    public Shotgun(int x, int y) {
        super(WeaponType.SHOTGUN, WeaponType.SHOTGUN.getDamage(), x, y);
    }

    @Override
    public void shoot() {
        DirectProjectile directProjectile = new DirectProjectile(this.getWeaponImage().getX(), this.getWeaponImage().getY(), 10, "assets/weapons/bullet", this, 5);
        this.addBullet(directProjectile);
    }
}