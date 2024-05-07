package sk.uniza.fri.game.enemy.ranged;

public class Worm extends RangedEnemy {

    public Worm(int health, int damage, int x, int y) {
        super(health, damage, x, y, "assets/enemies/worm/appear", 132, 100);
    }

    @Override
    public void charge() {
        switch (this.getState()) {
            case APPEAR:
                this.getEnemyImage().makeVisible();
                this.getImageLoader().changeDirectory("assets/enemies/worm/appear" + this.getSide());
                break;
            case IDLE:
                if (this.getTimeToIdle() == 0) {
                    this.setTimeToIdle(this.getRandom().nextInt(40, 80));
                    this.setIdleCount(0);
                }

                if (this.getIdleCount() < this.getTimeToIdle()) {
                    this.getImageLoader().changeDirectory("assets/enemies/worm/idle" + this.getSide());
                    this.getEnemyImage().changeImage(this.getImageLoader().getNextImage());
                    this.setIdleCount(this.getIdleCount() + 1);
                    return;
                } else {
                    this.setTimeToIdle(0);
                    this.setIdleCount(0);
                    this.setState(State.ATTACK);
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
            switch (this.getState()) {
                case APPEAR:
                    this.setState(State.IDLE);
                    break;
                case IDLE:
                    this.setState(State.ATTACK);
                    break;
                case ATTACK:
                    this.addProjectile("assets/enemies/worm/bullet", 0, 75, 25);

                    if (this.getRandom().nextInt(3) == 0) {
                        this.setState(State.IDLE);
                    } else {
                        this.setState(State.DISAPPEAR);
                    }
                    break;
                case DISAPPEAR:
                    this.setState(State.UNVISIBLE);
                    this.setInvisibleCount(this.getRandom().nextInt(1, 8));
                    break;
                case UNVISIBLE:
                    this.setInvisibleCount(this.getInvisibleCount() - 1);
                    if (this.getInvisibleCount() == 0) {
                        int side = this.getRandom().nextInt(2);
                        int randomSpawnX;
                        if (side == 0) {
                            randomSpawnX = this.getRandom().nextInt(100, 300);
                        } else {
                            randomSpawnX = this.getRandom().nextInt(600, 800);
                        }
                        this.getEnemyImage().changePosition(randomSpawnX, this.getEnemyImage().getY());
                        this.setState(State.APPEAR);
                    }
                    break;
            }
        }
    }

}
