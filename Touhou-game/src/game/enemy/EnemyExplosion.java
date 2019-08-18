package game.enemy;

import game.GameObject;
import game.renderer.Renderer;

public class EnemyExplosion extends GameObject {
    public EnemyExplosion() {
        renderer = new Renderer("assets/images/enemies/explosion"
                , 8, true);
    }
}
