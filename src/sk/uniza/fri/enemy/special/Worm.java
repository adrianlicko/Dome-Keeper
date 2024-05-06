package sk.uniza.fri.enemy.special;

import sk.uniza.fri.action.enemy.ActionAttackDome;
import sk.uniza.fri.enemy.Enemy;

import java.util.ArrayList;
import java.util.Random;

public class Worm extends Enemy implements SpecialEnemy {
    private WormState state;
    private int idleCount;
    private int timeToIdle;
    private int invisibleCount;
    private Random random;
    private ArrayList<EnemyProjectile> bullets;

    public Worm(int health, int damage, int x, int y) {
        super(health, damage, x, y, "assets/enemies/worm/appear", 100, 100);
        this.state = WormState.APPEAR;
        this.invisibleCount = 0;
        this.idleCount = 0;
        this.random = new Random();
        this.bullets = new ArrayList<>();
    }

    @Override
    public void specialMove() {
        this.moveProjectiles();

        switch (this.state) {
            case APPEAR:
                this.getEnemyImage().makeVisible();
                this.getImageLoader().changeDirectory("assets/enemies/worm/appear" + this.getSide());
                break;
            case IDLE:
                if (this.timeToIdle == 0) {
                    this.timeToIdle = this.random.nextInt(40, 80);
                    this.idleCount = 0;
                }

                if (this.idleCount < this.timeToIdle) {
                    this.getImageLoader().changeDirectory("assets/enemies/worm/idle" + this.getSide());
                    this.getEnemyImage().changeImage(this.getImageLoader().getNextImage());
                    this.idleCount++;
                    return;
                } else {
                    this.timeToIdle = 0;
                    this.idleCount = 0;
                    this.state = WormState.ATTACK;
                }
                break;
            case ATTACK:
                this.getImageLoader().changeDirectory("assets/enemies/worm/attack" + this.getSide());
                break;
            case DISAPPEAR:
                this.getImageLoader().changeDirectory("assets/enemies/worm/disappear" + this.getSide());
                break;
            case UNVISIBLE:
                this.getEnemyImage().makeInvisible();
                break;
        }

        String imagePath = this.getImageLoader().getNextImageWithoutLoop();
        if (imagePath != null) {
            this.getEnemyImage().changeImage(imagePath);
        } else {
            switch (this.state) {
                case APPEAR:
                    this.state = WormState.IDLE;
                    break;
                case IDLE:
                    this.state = WormState.ATTACK;
                    break;
                case ATTACK:
                    if (this.getSide().equals("/right")) {
                        this.bullets.add(new EnemyProjectile(this.getEnemyImage().getX() + 75, this.getEnemyImage().getY() + 25, "/right"));
                    } else {
                        this.bullets.add(new EnemyProjectile(this.getEnemyImage().getX(), this.getEnemyImage().getY() + 25, "/left"));
                    }
                    this.bullets.get(this.bullets.size() - 1).getBulletImage().makeVisible();

                    if (this.random.nextInt(3) == 0) {
                        this.state = WormState.IDLE;
                    } else {
                        this.state = WormState.DISAPPEAR;
                    }
                    break;
                case DISAPPEAR:
                    this.state = WormState.UNVISIBLE;
                    this.invisibleCount = this.random.nextInt(1, 8);
                    break;
                case UNVISIBLE:
                    this.invisibleCount--;
                    if (this.invisibleCount == 0) {
                        int side = this.random.nextInt(2);
                        int randomSpawnX;
                        if (side == 0) {
                            randomSpawnX = this.random.nextInt(100, 300);
                        } else {
                            randomSpawnX = this.random.nextInt(600, 800);
                        }
                        this.getEnemyImage().changePosition(randomSpawnX, this.getEnemyImage().getY());
                        this.state = WormState.APPEAR;
                    }
                    break;
            }
        }
    }

    public void moveProjectiles() {
        for (int i = 0; i < this.bullets.size(); i++) {
            this.bullets.get(i).move();
            if (this.bullets.get(i).getBulletImage().getX() < -50 || this.bullets.get(i).getBulletImage().getX() > 1024) {
                this.bullets.get(i).getBulletImage().makeInvisible();
                this.bullets.remove(this.bullets.get(i));
                System.out.println("Removed");
                continue;
            }
            ActionAttackDome.shootDome(this, this.bullets.get(i));
        }
    }

    public void removeProjectile(EnemyProjectile projectile) {
        projectile.getBulletImage().makeInvisible();
        this.bullets.remove(projectile);
    }

    @Override
    public boolean receiveDamage(int damage) {
        this.decreaseHealth(damage);
        if (this.getHealth() <= 0) {
            for (int i = 0; i < this.bullets.size(); i++) {
                this.bullets.get(i).getBulletImage().makeInvisible();
                this.bullets.remove(this.bullets.get(i));
            }
        }
        return this.getHealth() > 0;
    }
}

enum WormState {
    APPEAR,
    IDLE,
    ATTACK,
    DISAPPEAR,
    UNVISIBLE
}
