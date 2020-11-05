package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Target {
    private Texture texture;
    private float x = 640;
    private float y = 360;
    private boolean active = true;
    private float scale = 0.3f;

    public Target() {
        this.texture = new Texture("target.png");
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }

    public void update(float xShoot, float yShoot) {
        if (xShoot >= x - 100 * scale && xShoot <= x + 100 * scale && yShoot >= y - 100 * scale && yShoot <= y + 100 * scale) {
            deactivate();
        }
    }

    public void render(SpriteBatch batch) {
        if (isActive()) {
            batch.draw(texture, x - 87.5f, y - 87.5f, 87.5f, 87.5f, 175, 175, scale, scale, 0, 25, 25, 175, 175, false, false);
        }
    }

    public void dispose() {
        texture.dispose();
    }

}
