package com.mygdx.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ProjectileController {
    private static final int MAX_PROJECTILES = 100;
    private Projectile[] items;
    private boolean doubleShoot = false;

    public boolean isDoubleShoot() {
        return doubleShoot;
    }

    public Projectile[] getItems() {
        return items;
    }

    public ProjectileController(TextureAtlas atlas) {
        this.items = new Projectile[MAX_PROJECTILES];
        TextureRegion region = atlas.findRegion("projectile");
        for (int i = 0; i < items.length; i++) {
            items[i] = new Projectile(region);
        }
    }

    public void activate(float x, float y, float vx, float vy) {
        for (Projectile p : items) {
            if (!p.isActive()) {
                p.activate(x, y, vx, vy);
                return;
            }
        }
    }

    public void changeDoubleShootMode() {
        doubleShoot = !doubleShoot;
    }

    public void update(float dt) {
        for (Projectile p : items) {
            if (p.isActive()) {
                p.update(dt);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Projectile p : items) {
            if (p.isActive()) {
                p.render(batch);
            }
        }
    }
}
