package game.enemy;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {
    public int damage;

    public EnemyBullet() {
        this.renderer = new Renderer("assets/images/enemies/bullets");
        this.velocity.set(0, 4);
        this.hitBox = new BoxCollider(this, 16, 16);
        this.damage = 1;
    }

    @Override
    public void run() {
        super.run();
        this.checkPlayer();
        this.deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(this.position.y > Settings.GAME_HEIGHT + 50) {
            this.deactive();
        }
    }

    private void checkPlayer() {
        Player player = GameObject.findIntersects(Player.class
                , this.hitBox);
        if(player != null && !player.immune) {
            this.deactive();
            player.takeDamage(this.damage);
        }
    }
}
