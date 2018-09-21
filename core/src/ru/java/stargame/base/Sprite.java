package ru.java.stargame.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;

import ru.java.stargame.math.Rect;

public class Sprite extends Rect {

    protected  float angle;
    protected float scale = 1f;
    protected TextureRegion[] regions;

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    private int frame;

    public Sprite(TextureRegion[] regions) {
        if (regions == null)
            throw new NullPointerException("region == null");
        else {
            int len = regions.length;
            this.regions = new TextureRegion[len];
            System.arraycopy(regions,0,this.regions,0,len);
        }
    }

    public void draw(SpriteBatch batch){
        batch.draw(
           regions[frame],
           getLeft(), getBottom(), //точка отрисовки
           halfWidth, halfHeight,  //точка вращения
           getWidth(), getHeight(),
           scale, scale, //масштаб по х и по y
           angle     //угол поворота
        );
    }

    public void setHeightProportion(float height){
        setHeight(height);
        float aspect = regions[frame].getRegionWidth()/(float) regions[frame].getRegionHeight();
        setWidth(height*aspect);
    }

    public void resize(Rect worldBounds){

    }

    public boolean touchDown(Vector2 touch, int pointer) {

        return false;
    }

    public boolean update(float delta) {

        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean touchUp(Vector2 touch, int pointer) {

        return false;
    }


}
