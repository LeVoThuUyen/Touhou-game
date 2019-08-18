package game.enemy;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.renderer.Renderer;

import java.awt.*;

public class Enemy extends GameObject {
    int hp;

    public Enemy() {
        renderer = new Renderer("assets/images/enemies/level0/pink");
        position.set(-50, -50);
        velocity.set(0, 2);
        velocity.setAngle(Math.toRadians(20));
        hitBox = new BoxCollider(this, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
        hp = 3;
    }

    // nhap nhay
//    int countRender = 0;
//    @Override
//    public void render(Graphics g) {
//        countRender++;
//        if(countRender % 2 == 0) {
//            super.render(g);
//        }
//    }

    @Override
    public void run() {
        super.run();
        this.checkChangeDirection();
        this.fire();
        this.deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(this.position.y > Settings.GAME_HEIGHT + 50) {
            this.deactive();
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if(hp <= 0) {
            this.deactive();
        }
    }

    int count = 0;
    private void fire() {
        count++;
        if(count > 20) {
//            EnemyBullet bullet = new EnemyBullet();
            EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
            bullet.position.set(position.x, position.y);

            count = 0;
        }
    }

    private void checkChangeDirection() {
        if(this.outOfBoundRight() && this.onGoingRight()) {
            velocity.x = -velocity.x;
        }
        if(this.outOfBoundLeft() && this.onGoingLeft()) {
            velocity.x = -velocity.x;
        }
    }

    private boolean onGoingLeft() {
        return velocity.x < 0;
    }

    private boolean outOfBoundLeft() {
        return position.x <= 0;
    }

    private boolean onGoingRight() {
        return velocity.x > 0;
    }

    private boolean outOfBoundRight() {
        return position.x >= Settings.BACKGROUND_WIDTH - Settings.ENEMY_WIDTH;
    }

    @Override
    public void reset() {
        super.reset(); // active = true;
        position.set(-50, -50);
        velocity.set(0, 2);
        velocity.setAngle(Math.toRadians(20));
        hp = 3;
    }

    @Override
    public void deactive() {
        super.deactive();
        EnemyExplosion explosion = GameObject.recycle(EnemyExplosion.class);
        explosion.position.set(position.x, position.y);
    }
}
