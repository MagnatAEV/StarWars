package ru.java.stargame.pool;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.java.stargame.base.SpritesPool;
import ru.java.stargame.math.Rect;
import ru.java.stargame.sprites.Enemy;
import ru.java.stargame.sprites.MainShip;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Sound shootSound;
    private Sound boomSound;
    private MainShip mainShip;
    TextureAtlas atlas;
    Rect worldBounds;

    public EnemyPool(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound, Sound boomSound, MainShip mainShip, Rect worldBounds) {
        this.boomSound = boomSound;
        this.explosionPool = explosionPool;
        this.atlas = atlas;
        this.bulletPool = bulletPool;
        this.shootSound = shootSound;
        this.mainShip = mainShip;
        this.worldBounds = worldBounds;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(atlas, bulletPool, explosionPool, shootSound, boomSound, mainShip, worldBounds);
    }
}
