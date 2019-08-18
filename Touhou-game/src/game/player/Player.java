package game.player;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.physics.BoxCollider;
import game.renderer.Renderer;
import tklibs.Mathx;

import java.awt.*;

public class Player extends GameObject {
    public int hp;
    public boolean immune; // true >> bat tu, false >> binh thuong

    public Player() {
//        renderer = new Renderer("assets/images/players/straight/0.png");
        renderer = new Renderer("assets/images/players/straight");
        position.set(200, 200);
        this.hitBox = new BoxCollider(this
                , Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        hp = 3;
        immune = false;
    }

    public void takeDamage(int damage) {
        if(!immune) {
            hp -= damage;
            immune = true;
            if(hp <= 0) {
                hp = 0;
                this.deactive();
            }
        }
    }

    int countRender = 0;
    @Override
    public void render(Graphics g) {
        if(immune) {
            countRender++;
            if(countRender % 2 == 0) {
                super.render(g);
            }
        } else {
            super.render(g);
        }
    }

    @Override
    public void run() {
        super.run(); // velocity
        this.move();
        this.limitPosition();
        this.fire();
        this.checkImmune();
    }

    int countImmune = 0;
    private void checkImmune() {
        if(this.immune) {
            countImmune++;
            if(countImmune > 120) {
                immune = false;
            }
        } else {
            countImmune = 0;
        }
    }

    private void move() {
        double vx = 0;
        double vy = 0;
        double speed = 1;
        if(KeyEventPress.isUpPress) {
            vy -= speed;
        }
        if(KeyEventPress.isRightPress) {
            vx += speed;
        }
        if(KeyEventPress.isDownPress) {
            vy += speed;
        }
        if(KeyEventPress.isLeftPress) {
            vx -= speed;
        }
        velocity.set(vx, vy);
        velocity.setLength(speed);
    }

    private void limitPosition() {
        position.setX(Mathx.clamp(position.x, 0, Settings.BACKGROUND_WIDTH - Settings.PLAYER_WIDTH));
        position.setY(Mathx.clamp(position.y, 0, Settings.GAME_HEIGHT - Settings.PLAYER_HEIGHT));
    }

    int frameCount = 0;
    private void fire() {
        frameCount++;
        if(KeyEventPress.isFirePress && frameCount > 20) {
//            PlayerBullet bullet = new PlayerBullet();
//            bullet.position.set(position.x, position.y);
//            bullet.velocity.setAngle(Math.toRadians(-90));
//            bullets.add(bullet);
//
//            PlayerBullet bullet2 = new PlayerBullet();
//            bullet2.position.set(position.x + 20, position.y);
//            bullet2.velocity.setAngle(Math.toRadians(-45));
//            bullets.add(bullet2);
//
//            PlayerBullet bullet3 = new PlayerBullet();
//            bullet3.position.set(position.x - 20, position.y);
//            bullet3.velocity.setAngle(Math.toRadians(-135));
//            bullets.add(bullet3);
            int numberBullets = 5;
            double startAngle = Math.toRadians(-45);
            double endAngle = Math.toRadians(-135);
            double stepAngle = Math.abs(startAngle - endAngle) / (numberBullets - 1);
            double startX = position.x + 20;
            double endX = position.x - 20;
            double stepX = Math.abs(endX - startX) / (numberBullets - 1);

            for (int i = 0; i < numberBullets; i++) {
//                PlayerBullet bullet = new PlayerBullet();
                PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
                bullet.position.set(startX - stepX * i, position.y);
                bullet.velocity.setAngle(startAngle - stepAngle * i);
            }

            frameCount = 0;
        }
    }
}
