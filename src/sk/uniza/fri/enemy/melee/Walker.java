package sk.uniza.fri.enemy.melee;

public class Walker extends MeleeEnemy {

    public Walker(int health, int damage, int x, int y) {
        super(health, damage, x, y, "assets/enemies/walker/move", 124, 65);
    }

    @Override
    public void charge() {
        super.charge(100, 20, 0, 4, "assets/enemies/walker/attack");
    }

    @Override
    public boolean receiveDamage(int damage) {
        this.decreaseHealth(damage);
        return this.getHealth() > 0;
    }
}
