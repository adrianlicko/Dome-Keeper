package sk.uniza.fri.enemy.basic;

import sk.uniza.fri.enemy.Enemy;
import sk.uniza.fri.player.Dome;

public class Flyer extends Enemy implements BasicEnemy {

    public Flyer(int health, int damage, int x, int y) {
        super(health, damage, x, y, "assets/enemies/flyer/move", 110, 105);
    }

    @Override
    public void move() {
        // moves enemy if it is on the left side of the map
        if (this.getEnemyImage().getX() + 80 < Dome.getInstance().getDomeImage().getX()) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX() + 2, this.getEnemyImage().getY());

        // moves enemy if it is on the right side of the map
        } else if (this.getEnemyImage().getX() + 40 > Dome.getInstance().getDomeImage().getX() + 96) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX() - 2, this.getEnemyImage().getY());

        // moves enemy if it is on the top far away from dome
        } else if (this.getEnemyImage().getY() < 150) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX(), this.getEnemyImage().getY() + 1);

        } else {
            super.attack(4);
            this.getImageLoader().changeDirectory("assets/enemies/flyer/attack" + this.getSide());
            this.getEnemyImage().setHeight(150);
            this.getEnemyImage().changeImage(this.getImageLoader().getNextImage());
            return;
        }
        this.getEnemyImage().changeImage(this.getImageLoader().getNextImage());
    }

    @Override
    public boolean receiveDamage(int damage) {
        this.decreaseHealth(damage);
        return this.getHealth() > 0;
    }
}
