package sk.uniza.fri.game.enemies.ranged;

import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.actions.enemy.ActionAttackDome;
import sk.uniza.fri.game.enemies.Enemy;
import sk.uniza.fri.game.purchasables.weapons.projectiles.HomingProjectile;
import sk.uniza.fri.game.purchasables.weapons.projectiles.Projectile;

import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract class for enemies that attacks the dome on long range.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public abstract class RangedEnemy extends Enemy {
    private State state;
    private final Random random;
    private int idleCount;
    private int timeToIdle;
    private int invisibleCount;
    private ArrayList<Projectile> projectiles;

    /**
     * Constructor for the RangedEnemy class.
     *
     * @param health - Integer value representing the health of the enemy.
     * @param damage - Integer value representing the damage that the enemy deals.
     * @param enemyImageDirectory - String value representing the directory of the enemy images.
     * @param imageWidth - Integer value representing the width of the enemy image.
     * @param imageHeight - Integer value representing the height of the enemy image.
     */
    public RangedEnemy(int health, int damage, String enemyImageDirectory, int imageWidth, int imageHeight) {
        super(health, damage, enemyImageDirectory, imageWidth, imageHeight);
        this.state = State.APPEAR;
        this.random = new Random();
        this.projectiles = new ArrayList<>();
    }

    /**
     * Method that is responsible for spawning projectiles.
     *
     * @param directoryPath - String value representing the directory of the projectile images.
     * @param projectilePositionCorrectionFromLeft - Integer value representing the correction of the projectile position from the left side.
     * @param projectilePositionCorrectionFromRight - Integer value representing the correction of the projectile position from the right side.
     * @param elevation - Integer value representing the elevation of the projectile.
     * @param projectileWidth - Integer value representing the width of the projectile.
     * @param projectileHeight - Integer value representing the height of the projectile.
     */
    public void addProjectile(String directoryPath, int projectilePositionCorrectionFromLeft, int projectilePositionCorrectionFromRight, int elevation, int projectileWidth, int projectileHeight) {
        if (this.getSide().equals("/right")) {
            this.projectiles.add(new HomingProjectile(this.getEnemyImage().getX() + projectilePositionCorrectionFromRight, this.getEnemyImage().getY() + elevation, this.getDamage(), directoryPath, projectileWidth, projectileHeight, Game.getInstance().getDome().getDomeImage(), 2));
        } else {
            this.projectiles.add(new HomingProjectile(this.getEnemyImage().getX() + projectilePositionCorrectionFromLeft, this.getEnemyImage().getY() + elevation, this.getDamage(), directoryPath, projectileWidth, projectileHeight, Game.getInstance().getDome().getDomeImage(), 2));
        }
        this.projectiles.getLast().getProjectileImage().makeVisible();
    }

    /**
     * Method that is responsible for moving projectiles.
     * This method is managed by manager, so it's called constantly.
     */
    public void moveProjectiles() {
        for (int i = 0; i < this.projectiles.size(); i++) {
            this.projectiles.get(i).move();
            if (this.projectiles.get(i).getProjectileImage().getX() < -100 || this.projectiles.get(i).getProjectileImage().getX() > 1050) {
                this.projectiles.get(i).getProjectileImage().makeInvisible();
                this.projectiles.remove(this.projectiles.get(i));
                continue;
            }
            ActionAttackDome.shootDome(this, this.projectiles.get(i));
        }
    }

    public void removeProjectile(Projectile projectile) {
        projectile.getProjectileImage().makeInvisible();
        this.projectiles.remove(projectile);
    }

    public void removeAllProjectiles() {
        for (Projectile projectile : this.projectiles) {
            projectile.getProjectileImage().makeInvisible();
        }
        this.projectiles = new ArrayList<>();
    }

    /**
     * Method responsible for receiving damage.
     *
     * @param damage - Integer value representing the damage that the enemy receives.
     * @return - Boolean value representing if the enemy is still alive.
     */
    @Override
    public boolean receiveDamage(int damage) {
        this.decreaseHealth(damage);
        if (this.getHealth() <= 0) {
            this.removeAllProjectiles();
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
