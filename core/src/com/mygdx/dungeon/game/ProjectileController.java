package com.mygdx.dungeon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.dungeon.helpers.Assets;
import com.mygdx.dungeon.helpers.ObjectPool;


public class ProjectileController extends ObjectPool<Projectile> {
    private TextureRegion projectileTexture;

    public ProjectileController() {
        projectileTexture = Assets.getInstance().getAtlas().findRegion("projectile");
    }

    @Override
    protected Projectile newObject() {
        return new Projectile(projectileTexture);
    }

    public void activate(float x, float y, float vx, float vy) {
        getActiveElement().activate(x, y, vx, vy);
    }

    public void update(float dt) {
        for (Projectile p : getActiveList()) {
            p.update(dt);
        }
        checkPool();
    }

    public void render(SpriteBatch batch) {
        for (Projectile p : getActiveList()) {
            p.render(batch);
        }
    }
}
