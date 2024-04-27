package sk.uniza.fri.enemy.melee;

import sk.uniza.fri.enemy.Enemy;
import sk.uniza.fri.player.Dome;

public class Walker extends Enemy implements MeleeEnemy {

    public Walker(int health, int damage, int x, int y) {
        super(health, damage, x, y, "assets/enemies/Walker walk small.png");
    }

    @Override
    public void walk() {
        if (this.getX() + 35 < Dome.getInstance().getX()) {
            this.changePosition(this.getX() + 2, this.getY());
        } else if (this.getX() > Dome.getInstance().getX() + 96) {
            this.changePosition(this.getX() - 2, this.getY());
        } else {
            super.attack(2);
        }
    }

    @Override
    public boolean receiveDamage(int damage) {
        this.decreaseHealth(damage);
        return this.getHealth() > 0;
    }
}
