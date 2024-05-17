package sk.uniza.fri.game.enemy.ranged;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.action.enemy.ActionAttackDome;
import sk.uniza.fri.game.enemy.Enemy;
import sk.uniza.fri.game.player.Dome;
import sk.uniza.fri.game.purchasable.weapons.projectiles.HomingProjectile;

import java.util.ArrayList;
import java.util.Random;

public abstract class RangedEnemy extends Enemy {
    private State state;
    private Random random;
    private int idleCount;
    private int timeToIdle;
    private int invisibleCount;
    private ArrayList<HomingProjectile> projectiles;

    public RangedEnemy(int health, int damage, int x, int y, String enemyImageDirectory, int imageWidth, int imageHeight) {
        super(health, damage, x, y, enemyImageDirectory, imageWidth, imageHeight);
        this.state = State.APPEAR;
        this.random = new Random();
        this.projectiles = new ArrayList<>();
    }

    public abstract void charge();

    public void addProjectile(String directoryPath, int projectilePositionCorrectionFromLeft, int projectilePositionCorrectionFromRight, int elevation) {
        if (this.getSide().equals("/right")) {
            this.projectiles.add(new HomingProjectile(this.getEnemyImage().getX() + projectilePositionCorrectionFromRight, this.getEnemyImage().getY() + elevation, this.getDamage(), directoryPath, Game.getInstance().getDome().getDomeImage(), 2));
        } else {
            this.projectiles.add(new HomingProjectile(this.getEnemyImage().getX() + projectilePositionCorrectionFromLeft, this.getEnemyImage().getY() + elevation, this.getDamage(), directoryPath, Game.getInstance().getDome().getDomeImage(), 2));
        }
        this.projectiles.get(this.projectiles.size() - 1).getProjectileImage().makeVisible();
    }

    /**
     * Managed by manager
     */
    public void moveProjectiles() {
        for (int i = 0; i < this.projectiles.size(); i++) {
            this.projectiles.get(i).move();
            if (this.projectiles.get(i).getProjectileImage().getX() < -100 || this.projectiles.get(i).getProjectileImage().getX() > 1050) {
                this.projectiles.get(i).getProjectileImage().makeInvisible();
                this.projectiles.remove(this.projectiles.get(i));
                System.out.println("Removed");
                continue;
            }
            ActionAttackDome.shootDome(this, this.projectiles.get(i));
        }
    }

    public void removeProjectile(HomingProjectile projectile) {
        projectile.getProjectileImage().makeInvisible();
        this.projectiles.remove(projectile);
    }

    @Override
    public boolean receiveDamage(int damage) {
        this.decreaseHealth(damage);
        if (this.getHealth() <= 0) {
            for (int i = 0; i < this.projectiles.size(); i++) {
                this.projectiles.get(i).getProjectileImage().makeInvisible();
                this.projectiles.remove(this.projectiles.get(i));
            }
        }
        return this.getHealth() > 0;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Random getRandom() {
        return this.random;
    }

    public int getIdleCount() {
        return this.idleCount;
    }

    public void setIdleCount(int idleCount) {
        this.idleCount = idleCount;
    }

    public int getTimeToIdle() {
        return this.timeToIdle;
    }

    public void setTimeToIdle(int timeToIdle) {
        this.timeToIdle = timeToIdle;
    }

    public int getInvisibleCount() {
        return this.invisibleCount;
    }

    public void setInvisibleCount(int invisibleCount) {
        this.invisibleCount = invisibleCount;
    }
}
