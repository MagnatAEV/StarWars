package ru.java.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import javax.swing.plaf.synth.Region;

import ru.java.stargame.base.Sprite;
import ru.java.stargame.math.Rect;

public class BattleCruser extends Sprite {

    Vector2 position;
    boolean press;
    int pointer;
    Vector2 posTouch;
    Rect worldBound;
    TextureAtlas atlas;

    public BattleCruser(TextureAtlas atlas,TextureRegion region, float scale) {
        super(region);
        this.atlas = atlas;
        setHeightProportion(scale);
        posTouch = new Vector2();
    }


    @Override
    public void resize(Rect worldBound) {
        this.worldBound = worldBound;
        position = new Vector2(-this.getWidth()/2, - this.worldBound.getHeight()/2 + 0.05f);
        setBottom(position.y);
        setLeft(position.x);
    }

    public void update(){
        setBottom(position.y);
        setLeft(position.x);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (!press && isMe(touch)){
            press = true;
            this.pointer = pointer;
            posTouch.set(touch);
        }
        return true;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer == pointer) {
            press = false;
        }
        return press;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        if (/*isMe(touch) && */press) {
            Vector2 delta = new Vector2(touch);
            delta.sub(posTouch);
            posTouch.set(touch);
            position.add(delta);
            if (position.x + this.getHalfWidth() <= worldBound.getLeft()) position.x = worldBound.getLeft() - this.getHalfWidth();
            if (position.x + this.getHalfWidth() >= worldBound.getRight()) position.x = worldBound.getRight()-this.getHalfWidth();
            if (position.y + this.getHeight() >= worldBound.getTop()) position.y = worldBound.getTop() - this.getHeight();
            if (position.y <= worldBound.getBottom()) position.y = worldBound.getBottom();


        }
        return true;
    }
}
