package sk.uniza.fri.game.weapons.player;

import sk.uniza.fri.game.weapons.Bullet;
import sk.uniza.fri.game.weapons.Weapon;

public class Shotgun extends Weapon {

    public Shotgun(int x, int y) {
        super(WeaponType.SHOTGUN, WeaponType.SHOTGUN.getDamage(), x, y);
    }

    @Override
    public void shoot() {
        Bullet bullet = new Bullet(this, this.getWeaponImage().getX(), this.getWeaponImage().getY(), 8);
        super.addBullet(bullet);
    }
}