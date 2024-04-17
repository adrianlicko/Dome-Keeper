package sk.uniza.fri.weapons.player;

import sk.uniza.fri.weapons.Weapon;

public class Shotgun extends Weapon {

    public Shotgun(int x, int y) {
        super(WeaponType.SHOTGUN, WeaponType.SHOTGUN.getDamage(), x, y);
    }

    @Override
    public void shoot() {

    }
}
