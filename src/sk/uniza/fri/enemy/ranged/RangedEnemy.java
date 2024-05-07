package sk.uniza.fri.enemy.ranged;

import sk.uniza.fri.action.enemy.ActionAttackDome;
import sk.uniza.fri.enemy.Enemy;

import java.util.ArrayList;

public abstract class RangedEnemy extends Enemy {
    private ArrayList<EnemyProjectile> projectiles;

    public RangedEnemy(int health, int damage, int x, int y, String enemyImageDirectory, int imageWidth, int imageHeight) {
        super(health, damage, x, y, enemyImageDirectory, imageWidth, imageHeight);
        this.projectiles = new ArrayList<>();
    }

    public abstract void charge();

    public void addProjectile(int projectilePositionCorrectionFromLeft, int projectilePositionCorrectionFromRight) {
        if (this.getSide().equals("/right")) {
            this.projectiles.add(new EnemyProjectile(this.getEnemyImage().getX() + projectilePositionCorrectionFromRight, this.getEnemyImage().getY() + 25, "/right"));
        } else {
            this.projectiles.add(new EnemyProjectile(this.getEnemyImage().getX(), this.getEnemyImage().getY() + 25, "/left"));
        }
        this.projectiles.get(this.projectiles.size() - 1).getBulletImage().makeVisible();
    }

    /**
     * Managed by manager
     */
    public void moveProjectiles() {
        for (int i = 0; i < this.projectiles.size(); i++) {
            this.projectiles.get(i).move();
            if (this.projectiles.get(i).getBulletImage().getX() < -100 || this.projectiles.get(i).getBulletImage().getX() > 1050) {
                this.projectiles.get(i).getBulletImage().makeInvisible();
                this.projectiles.remove(this.projectiles.get(i));
                System.out.println("Removed");
                continue;
            }
            ActionAttackDome.shootDome(this, this.projectiles.get(i));
        }
    }

    public void removeProjectile(EnemyProjectile projectile) {
        projectile.getBulletImage().makeInvisible();
        this.projectiles.remove(projectile);
    }

    @Override
    public boolean receiveDamage(int damage) {
        this.decreaseHealth(damage);
        if (this.getHealth() <= 0) {
            for (int i = 0; i < this.projectiles.size(); i++) {
                this.projectiles.get(i).getBulletImage().makeInvisible();
                this.projectiles.remove(this.projectiles.get(i));
            }
        }
        return this.getHealth() > 0;
    }
}
