package com.mygdx.dungeon.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.dungeon.helpers.Assets;


public class Hero extends Unit {
    private String name;

    public Hero(GameController gc) {
        super(gc, 1, 1, 10);
        this.name = "Lancelot";
        this.hpMax = 100;
        this.hp = this.hpMax;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
        this.gold = 0;
    }

    public void update(float dt) {
        super.update(dt);
        if (Gdx.input.justTouched() && canIMakeAction()) {
            Monster m = gc.getUnitController().getMonsterController().getMonsterInCell(gc.getCursorX(), gc.getCursorY());
            if (m != null && canIAttackThisTarget(m)) {
                attack(m);
                if (m.getHp() <= 0) {
                    this.gold += m.giveGold();
                }
            } else {
                goTo(gc.getCursorX(), gc.getCursorY());
            }
        }
    }

    public String getName() {
        return name;
    }
}
