package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Tank {
    private Texture texture;
    private Texture textureWeapon;
    private float x;
    private float y;
    private float speed;
    private float angle;
    private float angleWeapon;
    private Projectile projectile;
    private float scale;

    public Tank() {
        this.texture = new Texture("tank.png");
        this.textureWeapon = new Texture("weapon.png");
        this.projectile = new Projectile();
        this.x = 100.0f;
        this.y = 100.0f;
        this.speed = 240.0f;
        this.scale = 3.0f;
    }

    public void update(float dt) {
        float i;
        float j;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angle -= 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angle += 90.0f * dt;
        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
//            angle -= 90.0f;
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
//            angle += 90.0f;
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            i = x + speed * MathUtils.cosDeg(angle) * dt;
            if (i - (20 * scale) > 0 && i + (20 * scale) < Gdx.graphics.getWidth()) {
                x += speed * MathUtils.cosDeg(angle) * dt;
            }
            j = y + speed * MathUtils.sinDeg(angle) * dt;
            if (j - (20 * scale) > 0 && j + (20 * scale) < Gdx.graphics.getHeight()) {
                y += speed * MathUtils.sinDeg(angle) * dt;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            i = x - speed * MathUtils.cosDeg(angle) * dt * 0.2f;
            if (i - (20 * scale) > 0 && i + (20 * scale) < Gdx.graphics.getWidth()) {
                x -= speed * MathUtils.cosDeg(angle) * dt * 0.2f;
            }
            j = y - speed * MathUtils.sinDeg(angle) * dt * 0.2f;
            if (j - (20 * scale) > 0 && j + (20 * scale) < Gdx.graphics.getHeight()) {
                y -= speed * MathUtils.sinDeg(angle) * dt * 0.2f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            angleWeapon -= 90.0f * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            angleWeapon += 90.0f * dt;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !projectile.isActive()) {
            projectile.shoot(x + 16 * scale * MathUtils.cosDeg(angle), y + 16 * scale * MathUtils.sinDeg(angle), angle + angleWeapon);
        }
        if (projectile.isActive()) {
            projectile.update(dt);
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x - 20, y - 20, 20, 20, 40, 40, scale, scale, angle, 0, 0, 40, 40, false, false);
        batch.draw(textureWeapon, x - 20, y - 20, 20, 20, 40, 40, scale, scale, angle + angleWeapon, 0, 0, 40, 40, false, false);
        if (projectile.isActive()) {
            projectile.render(batch);
        }
    }

    public void dispose() {
        texture.dispose();
        projectile.dispose();
    }
}
