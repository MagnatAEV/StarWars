package ru.java.stargame.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.java.stargame.base.Ship;
import ru.java.stargame.math.Rect;
import ru.java.stargame.pool.BulletPool;
import ru.java.stargame.pool.ExplosionPool;


public class Enemy extends Ship {

    private MainShip mainShip;

    private Vector2 v0 = new Vector2();
    private ExplosionPool explosionPool;
    private static final float FAST_MODE_BIG_SHIP = -0.1f;
    private float bulletVY;

    public Enemy(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound, Sound boomSound, MainShip mainShip, Rect worldBounds) {
        super(atlas.findRegion("bulletMainShip"), bulletPool, shootSound);
        this.explosionPool = explosionPool;
        this.boomSound = boomSound;
        this.mainShip = mainShip;
        this.bulletHeight = 0.01f;
        this.bulletDamage = 1;
        this.bulletV.set(0, 0.5f);
        this.reloadInterval = 0.5f;
        this.worldBounds = worldBounds;
        //this.v.set(v0);
        //setHeightProportion(0.15f);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (getTop() >= 0.5f) {
            setVelocity(FAST_MODE_BIG_SHIP);
        } else {
            v.set(v0);
        }
        if (getBottom() <= -0.5f){
            this.destroy();
            Explosion explosion = explosionPool.obtain();
            explosion.set(0.2f, pos, boomSound);
            explosion.play();
        }
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        setHeightProportion(height);
        reloadTimer = reloadInterval;
        v.set(v0);
    }

    private void setVelocity(float v){
        this.v.set(0, v);
    }

}
