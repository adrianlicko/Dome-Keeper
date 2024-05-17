package sk.uniza.fri.game.enemy.melee;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemy.Enemy;

public abstract class MeleeEnemy extends Enemy {

    public MeleeEnemy(int health, int damage, String enemyImageDirectory, int imageWidth, int imageHeight) {
        super(health, damage, enemyImageDirectory, imageWidth, imageHeight);
    }

    protected void charge(int enemyPositionCorrectionFromLeft, int enemyPositionCorrectionFromRight, int enemyPositionCorrectionFromTop, int attackSpeed, String attackImageDirectory) {
        // moves enemy if it is on the left side of the map
        if (this.getEnemyImage().getX() + enemyPositionCorrectionFromLeft < Game.getInstance().getDome().getDomeImage().getX()) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX() + 2, this.getEnemyImage().getY());

            // moves enemy if it is on the right side of the map
        } else if (this.getEnemyImage().getX() + enemyPositionCorrectionFromRight > Game.getInstance().getDome().getDomeImage().getX() + 96) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX() - 2, this.getEnemyImage().getY());

            // moves enemy if it is on the top far away from dome
        } else if (this.getEnemyImage().getY() < enemyPositionCorrectionFromTop) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX(), this.getEnemyImage().getY() + 1);

        } else {
            super.attack(attackSpeed);
            this.getImageLoader().changeDirectory(attackImageDirectory + this.getSide());
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
