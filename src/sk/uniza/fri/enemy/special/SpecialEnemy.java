package sk.uniza.fri.enemy.special;

public interface SpecialEnemy {
    void specialMove();
    void moveProjectiles();
    void removeProjectile(EnemyProjectile projectile);
    int getDamage();
}
