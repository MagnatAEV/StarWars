package ru.java.stargame.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.java.stargame.base.Sprite;

public class Explosion extends Sprite {

    private float animateInterval = 0.017f;
    private float animateTimer;
    private Sound soundBoom;

    public Explosion(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    public void set(float height, Vector2 pos, Sound soundBoom) {
        this.pos.set(pos);
        this.soundBoom = soundBoom;
        setHeightProportion(height);
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= animateInterval) {
            animateTimer = 0f;
            if (++frame == regions.length) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0;
    }

    public void play(){
        soundBoom.play();
    }
}
