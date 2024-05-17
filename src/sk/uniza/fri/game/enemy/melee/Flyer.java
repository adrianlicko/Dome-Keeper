package sk.uniza.fri.game.enemy.melee;

import java.util.Random;

public class Flyer extends MeleeEnemy {

    public Flyer(int health, int damage) {
        super(health, damage, "assets/enemies/flyer/move", 110, 105);
    }

    @Override
    public void charge() {
        super.charge(80, 40, 150, 4, "assets/enemies/flyer/attack");
    }

    @Override
    public void randomSpawn() {
        var random = new Random();
        if (random.nextBoolean()) {
            this.getEnemyImage().changePosition(-75, random.nextInt(-10, 160));
        } else {
            this.getEnemyImage().changePosition(980, random.nextInt(-10, 160));
        }
    }
}
