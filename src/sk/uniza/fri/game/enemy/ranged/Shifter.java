package sk.uniza.fri.game.enemy.ranged;

public class Shifter extends RangedEnemy {

    public Shifter(int health, int damage, int x, int y) {
        super(health, damage, x, y, "assets/enemies/shifter/appear", 96, 80);
    }

    @Override
    public void charge() {
        switch (this.getState()) {
            case APPEAR:
                this.getEnemyImage().makeVisible();
                this.getImageLoader().changeDirectory("assets/enemies/shifter/appear" + this.getSide());
                break;
            case IDLE:
                if (this.getTimeToIdle() == 0) {
                    this.setTimeToIdle(this.getRandom().nextInt(40, 80));
                    this.setIdleCount(0);
                }

                if (this.getIdleCount() < this.getTimeToIdle()) {
                    this.getImageLoader().changeDirectory("assets/enemies/shifter/idle" + this.getSide());
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
                this.getImageLoader().changeDirectory("assets/enemies/shifter/attack" + this.getSide());
                break;
            case DISAPPEAR:
                this.getImageLoader().changeDirectory("assets/enemies/shifter/disappear" + this.getSide());
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
                    this.addProjectile("assets/enemies/shifter/bullet", -40, 30, 5, 106, 100);

                    if (this.getRandom().nextInt(3) == 0) {
                        this.setState(State.IDLE);
                    } else {
                        this.setState(State.DISAPPEAR);
                    }
                    break;
                case DISAPPEAR:
                    this.setState(State.UNVISIBLE);
                    this.setInvisibleCount(1);
                    break;
                case UNVISIBLE:
                    this.setInvisibleCount(this.getInvisibleCount() - 1);
                    if (this.getInvisibleCount() == 0) {
                        int side = this.getRandom().nextInt(2);
                        int randomSpawnX;
                        if (side == 0) {
                            randomSpawnX = this.getRandom().nextInt(50, 320);
                        } else {
                            randomSpawnX = this.getRandom().nextInt(570, 850);
                        }

                        int randomSpawnY = this.getRandom().nextInt(0, 240);

                        this.getEnemyImage().changePosition(randomSpawnX, randomSpawnY);
                        this.setState(State.APPEAR);
                    }
                    break;
            }
        }
    }
}
