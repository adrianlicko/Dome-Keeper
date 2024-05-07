package sk.uniza.fri.game.enemy.melee;

public class Flyer extends MeleeEnemy {

    public Flyer(int health, int damage, int x, int y) {
        super(health, damage, x, y, "assets/enemies/flyer/move", 110, 105);
    }

    @Override
    public void charge() {
        super.charge(80, 40, 150, 4, "assets/enemies/flyer/attack");
    }

    @Override
    public boolean receiveDamage(int damage) {
        this.decreaseHealth(damage);
        return this.getHealth() > 0;
    }
}
