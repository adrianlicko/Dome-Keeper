package sk.uniza.fri.enemy.ranged;

import java.util.Random;

public class Worm extends RangedEnemy {
    private State state;
    private Random random;
    private int idleCount;
    private int timeToIdle;
    private int invisibleCount;

    public Worm(int health, int damage, int x, int y) {
        super(health, damage, x, y, "assets/enemies/worm/appear", 100, 100);
        this.state = State.APPEAR;
        this.random = new Random();
    }

    @Override
    public void charge() {
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
                    this.state = State.ATTACK;
                    return;
                }
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
                    this.state = State.IDLE;
                    break;
                case IDLE:
                    this.state = State.ATTACK;
                    break;
                case ATTACK:
                    this.addProjectile(0, 75);

                    if (this.random.nextInt(3) == 0) {
                        this.state = State.IDLE;
                    } else {
                        this.state = State.DISAPPEAR;
                    }
                    break;
                case DISAPPEAR:
                    this.state = State.UNVISIBLE;
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
                        this.state = State.APPEAR;
                    }
                    break;
            }
        }
    }

}
