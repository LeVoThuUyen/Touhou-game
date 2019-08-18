package game.player.item;

import game.GameObject;
import game.Settings;

import java.util.Random;

public class ItemSummoner extends GameObject {
    public int delayFrame;

    public ItemSummoner() {
        this.delayFrame = this.randomDelayFrame();
    }

    @Override
    public void run() {
        super.run();
        this.summonItem();
    }

    int count = 0;
    private void summonItem() {
        count++;
        if(count > delayFrame) {
            ItemHp item = GameObject.recycle(ItemHp.class);
            int randomX = new Random().nextInt(Settings.BACKGROUND_WIDTH);
            item.position.set(randomX, -50);

            int randomVelocityY = new Random().nextInt(3) + 3;
            item.velocity.set(0, randomVelocityY);

            count = 0;
            delayFrame = randomDelayFrame();
        }
    }

    private int randomDelayFrame() {
        // 180 > 360
        Random random = new Random();
        return random.nextInt(180) + 180;
    }
}
