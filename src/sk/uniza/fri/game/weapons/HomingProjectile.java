package sk.uniza.fri.game.weapons;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.Game;
import sk.uniza.fri.game.enemy.Enemy;

public class HomingProjectile extends Projectile {
    private Weapon weapon;
    private int speed;
    private double angle;
    private ImageObject target;
    private boolean isFiredByDome;
    private int initialMoves;
    private double dirX;
    private double dirY;
    private boolean isFiredDuringEnemyWasAlive;

    public HomingProjectile(int x, int y, int damage, String directoryPath, ImageObject target, int speed) {
        super(x, y, damage, directoryPath);
        this.speed = speed;
        this.target = target;
        this.isFiredByDome = false;
    }

    public HomingProjectile(int x, int y, int damage, String directoryPath, Weapon weapon, int speed) {
        super(x, y, damage, directoryPath);
        this.weapon = weapon;
        this.speed = speed;
        if (this.weapon.isReversed()) {
            this.angle = Math.toRadians(180) + Math.toRadians(this.weapon.getAngle());
        } else {
            this.angle = Math.toRadians(this.weapon.getAngle());
        }
        this.isFiredByDome = true;
        this.initialMoves = 5;
    }

    private void calculateDirectionVector() {
        // Calculate the direction vector from the projectile to the target
        this.dirX = this.target.getHitX() - this.getProjectileImage().getHitX();
        this.dirY = this.target.getHitY() - this.getProjectileImage().getHitY();

        // Normalize the direction vector
        double length = Math.sqrt(this.dirX * this.dirX + this.dirY * this.dirY);
        this.dirX /= length;
        this.dirY /= length;
    }

    private void moveInDirection() {
        // Move the projectile in the direction of the target
        this.getProjectileImage().addX((int)(this.dirX * this.speed));
        this.getProjectileImage().addY((int)(this.dirY * this.speed));
    }

    @Override
    public void move() {
        if (this.isFiredByDome) {
            if (this.initialMoves > 0) {
                this.isFiredDuringEnemyWasAlive = !Game.getInstance().getEnemies().isEmpty();

                // Move like a DirectProjectile
                this.getProjectileImage().addX((int)(this.speed * Math.cos(this.angle)));
                this.getProjectileImage().addY((int)(this.speed * Math.sin(this.angle)));
                this.initialMoves--;
            } else if (!this.isFiredDuringEnemyWasAlive) {
                this.getProjectileImage().addX((int)(this.speed * Math.cos(this.angle)));
                this.getProjectileImage().addY((int)(this.speed * Math.sin(this.angle)));
            } else {
                // Move like a HomingProjectile
                Enemy closestEnemy = null;
                for (Enemy enemy : Game.getInstance().getEnemies()) {
                    if (closestEnemy == null) {
                        closestEnemy = enemy;
                    } else {
                        if (Math.sqrt(Math.pow(this.getProjectileImage().getHitX() - enemy.getEnemyImage().getHitX(), 2) + Math.pow(this.getProjectileImage().getHitY() - enemy.getEnemyImage().getHitY(), 2)) <
                                Math.sqrt(Math.pow(this.getProjectileImage().getHitX() - closestEnemy.getEnemyImage().getHitX(), 2) + Math.pow(this.getProjectileImage().getHitY() - closestEnemy.getEnemyImage().getHitY(), 2))) {
                            closestEnemy = enemy;
                        }
                    }
                }
                if (closestEnemy != null) {
                    this.target = closestEnemy.getEnemyImage();
                } else {
                    this.moveInDirection();
                    this.updateProjectile();
                    return;
                }

                this.calculateDirectionVector();
                this.moveInDirection();
            }
        } else {
            this.calculateDirectionVector();
            this.moveInDirection();
        }

        this.updateProjectile();
    }

    private void updateProjectile() {
        // Update the image and position of the projectile
        this.getProjectileImage().changeImage(this.getImageLoader().getNextImage());
        this.getProjectileImage().changePosition(this.getProjectileImage().getX(), this.getProjectileImage().getY());
    }
}