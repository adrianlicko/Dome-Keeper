package sk.uniza.fri.weapons.player;

import sk.uniza.fri.weapons.Bullet;
import sk.uniza.fri.weapons.Weapon;

public class Shotgun extends Weapon {

    public Shotgun(int x, int y) {
        super(WeaponType.SHOTGUN, WeaponType.SHOTGUN.getDamage(), x, y);
    }

    @Override
    public void shoot() {
        Bullet bullet = new Bullet(this, this.getX(), this.getY(), 8);
        super.addBullet(bullet);
    }
}