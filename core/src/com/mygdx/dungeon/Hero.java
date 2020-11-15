package com.mygdx.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private ProjectileController projectileController;
    private Vector2 position;
    private Vector2 velocity;
    private TextureRegion texture;
    private float direction = 200f;
    private float step = 40f;
    private float mapHeight = 20 * 40f;
    private float mapWidth = 20 * 40f;


    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(100, 100);
        this.velocity = new Vector2(direction, 0);
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
    }


    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) projectileController.changeDoubleShootMode();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (projectileController.isDoubleShoot()) {
                projectileController.activate(position.x, position.y, velocity.x, velocity.y);
                projectileController.activate(position.x, position.y, velocity.x, velocity.y);
            }
            projectileController.activate(position.x, position.y, velocity.x, velocity.y);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            position.y += step;
            velocity.set(0, direction);
            if (position.y + 20 > mapHeight) position.y = mapHeight - 20;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            position.x -= step;
            velocity.set(-direction, 0);
            if (position.x - 20 < 0) position.x = 20;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            position.y -= step;
            velocity.set(0, -direction);
            if (position.y - 20 < 0) position.y = 20;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            position.x += step;
            velocity.set(direction, 0);
            if (position.x + 20 > mapWidth) position.x = mapWidth - 20;
        }
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 20, position.y - 20);
    }
}
