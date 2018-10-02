package ru.java.stargame.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.java.stargame.math.Rect;
import ru.java.stargame.pool.BulletPool;
import ru.java.stargame.sprites.Bullet;

public class Ship extends Sprite {

    protected Vector2 v = new Vector2();

    protected Rect worldBounds;

    protected Vector2 bulletV = new Vector2();
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected float bulletHeight;
    protected int bulletDamage;

    protected Sound shootSound;
    protected Sound boomSound;

    protected float reloadInterval;
    protected float reloadTimer;

    protected int hp;

    public Ship(TextureRegion region, int rows, int cols, int frames, BulletPool bulletPool, Sound shootSound) {
        super(region, rows, cols, frames);
        this.bulletPool = bulletPool;
        this.shootSound = shootSound;
        this.bulletHeight = 0.01f;
        this.bulletDamage = 1;
    }

    public Ship(TextureRegion region, BulletPool bulletPool, Sound shootSound) {
        this.bulletRegion = region;
        this.bulletPool = bulletPool;
        this.shootSound = shootSound;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
        shootSound.play();
    }
}
