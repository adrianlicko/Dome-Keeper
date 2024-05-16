package sk.uniza.fri.game.weapons.player;

import sk.uniza.fri.game.weapons.DirectProjectile;
import sk.uniza.fri.game.weapons.HomingProjectile;
import sk.uniza.fri.game.weapons.Weapon;

public class MagicWand extends Weapon implements Purchasable {
    public MagicWand() {
        super(WeaponType.MAGIC_WAND, WeaponType.MAGIC_WAND.getDamage(), WeaponType.MAGIC_WAND.getFireRate());
    }

    @Override
    public void fire() {
        super.shoot();
        HomingProjectile homingProjectile = new HomingProjectile(this.getImage().getX(), this.getImage().getY(), 10, WeaponType.MAGIC_WAND.getProjectileImageDirectory(), this, 5);
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
