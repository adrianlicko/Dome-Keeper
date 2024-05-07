package sk.uniza.fri.game.enemy.melee;

import sk.uniza.fri.game.enemy.Enemy;
import sk.uniza.fri.game.player.Dome;

public abstract class MeleeEnemy extends Enemy {

    public MeleeEnemy(int health, int damage, int x, int y, String enemyImageDirectory, int imageWidth, int imageHeight) {
        super(health, damage, x, y, enemyImageDirectory, imageWidth, imageHeight);
    }

    protected void charge(int enemyPositionCorrectionFromLeft, int enemyPositionCorrectionFromRight, int enemyPositionCorrectionFromTop, int attackSpeed, String attackImageDirectory) {
        // moves enemy if it is on the left side of the map
        if (this.getEnemyImage().getX() + enemyPositionCorrectionFromLeft < Dome.getInstance().getDomeImage().getX()) {
            this.getEnemyImage().changePosition(this.getEnemyImage().getX() + 2, this.getEnemyImage().getY());

            // moves enemy if it is on the right side of the map
        } else if (this.getEnemyImage().getX() + enemyPositionCorrectionFromRight > Dome.getInstance().getDomeImage().getX() + 96) {
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
        return false;
    }
}
