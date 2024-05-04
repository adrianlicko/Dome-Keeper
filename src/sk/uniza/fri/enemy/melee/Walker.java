package sk.uniza.fri.enemy.melee;

import sk.uniza.fri.enemy.Enemy;
import sk.uniza.fri.player.Dome;

public class Walker extends Enemy implements MeleeEnemy {

    public Walker(int health, int damage, int x, int y) {
        super(health, damage, x, y, "assets/enemies/Walker walk small.png");
    }

    @Override
    public void walk() {
        if (this.getEnemyImage().getX() + 35 < Dome.getInstance().getDomeImage().getX()) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX() + 2, this.getEnemyImage().getY());
        } else if (this.getEnemyImage().getX() > Dome.getInstance().getDomeImage().getX() + 96) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX() - 2, this.getEnemyImage().getY());
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
