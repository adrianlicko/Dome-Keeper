package sk.uniza.fri.game.purchasable.specialItems;

import sk.uniza.fri.ImageObject;
import sk.uniza.fri.game.player.HUD;

import java.util.Timer;
import java.util.TimerTask;

public class HealingRing extends SpecialItem {
    private int totalAmount;
    private Timer timer;
    private int delay;

    public HealingRing(int amount, int totalAmount, int delay) {
        super(amount);
        this.totalAmount = totalAmount;
        this.delay = delay;
        this.timer = new Timer();
    }

    @Override
    public void use() {
        this.setEquipped(true);

        TimerTask task = new TimerTask() {
            private int timesRun = 0;

            @Override
            public void run() {
                if (this.timesRun < HealingRing.this.totalAmount) {
                    HUD.getInstance().increaseDomeHealth(HealingRing.this.getAmount());
                    this.timesRun++;
                } else {
                    HealingRing.this.timer.cancel(); // stop the timer when it has run healingTime times
                }
            }
        };

        // schedule the task to run every 3 seconds (3000 milliseconds)
        HealingRing.this.timer.scheduleAtFixedRate(task, 0, HealingRing.this.delay * 1000L);
    }

    @Override
    public ImageObject getImage() {
        return new ImageObject("assets/items/HealingRing small.png");
    }

    @Override
    public String getName() {
        return "Healing Ring";
    }

    @Override
    public String getDescription() {
        return "Heals you slowly over time";
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getPrice() {
        return this.getAmount() * (this.delay * this.totalAmount / 5);
    }
}
