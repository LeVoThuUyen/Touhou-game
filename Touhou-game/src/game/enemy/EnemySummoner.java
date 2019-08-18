package game.enemy;

import game.GameObject;

public class EnemySummoner extends GameObject {
    public EnemySummoner() {}

    int frameCount = 0;
    @Override
    public void run() {
        super.run();
        this.summonEnemy();
    }

    private void summonEnemy() {
        frameCount++;
        if(frameCount > 120) {
//            new Enemy();
            GameObject.recycle(Enemy.class);
            frameCount = 0;
        }
    }
}
