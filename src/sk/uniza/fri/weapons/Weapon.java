package sk.uniza.fri.weapons;

import fri.shapesge.Image;
import sk.uniza.fri.action.player.ActionAttackEnemy;
import sk.uniza.fri.weapons.player.WeaponType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Weapon {
    private Image weaponImage;
    private int damage;
    private int weaponAngle;
    private boolean isReversed;
    private int x;
    private int y;
    private List<Bullet> bullets;

    public Weapon(WeaponType weaponType, int damage, int x, int y) {
        this.weaponImage = new Image(weaponType.getImagePath(), x, y);
        this.weaponImage.makeVisible();
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.bullets = new ArrayList<>();
    }

    public abstract void shoot();

    public void moveBullets() {
        for (int i = 0; i < this.bullets.size(); i++) {
            this.bullets.get(i).move();
            if (this.bullets.get(i).getX() < -50 || this.bullets.get(i).getX() > 1024 || this.bullets.get(i).getY() < 0) {
                this.removeBullet(this.bullets.get(i));
                continue;
            }
            ActionAttackEnemy.shoot(this, this.bullets.get(i));
        }
    }

    public void changePosition(int x, int y) {
        this.weaponImage.changePosition(x, y);
        this.x = x;
        this.y = y;
    }

    public void changeAngle(int posOrNegNum, int weaponSliderX) {
        int sliderX = -weaponSliderX * 2;
        this.weaponAngle += posOrNegNum * 2;

        if (sliderX == -70) {
            if (!this.isReversed) {
                this.weaponAngle += 140;
                this.isReversed = true;
                this.weaponImage.changeImage(WeaponType.SHOTGUN.getReverseImagePath());
            } else {
                this.weaponAngle -= 140;
                this.isReversed = false;
                this.weaponImage.changeImage(WeaponType.SHOTGUN.getImagePath());
            }
        }
        this.weaponImage.changeAngle(this.weaponAngle);
    }

    public int getAngle() {
        return this.weaponAngle;
    }

    public boolean isReversed() {
        return this.isReversed;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    protected void addBullet(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        bullet.makeInvisible();
        this.bullets.remove(bullet);
    }
}
