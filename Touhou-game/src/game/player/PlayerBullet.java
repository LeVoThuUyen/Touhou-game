package game.player;

import game.GameObject;
import game.Settings;
import game.enemy.Enemy;
import game.physics.BoxCollider;
import game.renderer.Renderer;

import java.awt.*;

public class PlayerBullet extends GameObject {
    int damage;

    public PlayerBullet() {
        renderer = new Renderer("assets/images/player-bullets/a", 4, false);
        velocity.set(0, -3);
        hitBox = new BoxCollider(this, Settings.PLAYER_BULLET_WIDTH, Settings.PLAYER_BULLET_HEIGHT);
        damage = 1;
    }

    @Override
    public void run() {
        super.run();
        this.checkEnemy();
        this.deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(position.y < -50) {
            this.deactive();
        }
    }

    private void checkEnemy() {
        Enemy enemy = GameObject.findIntersects(Enemy.class
                , this.hitBox);
        if(enemy != null) {
            this.deactive();
            enemy.takeDamage(damage);
            // tinh diem neu giet duoc enemy
            if(!enemy.active) {
                Settings.score++;
            }
        }
    }
}
