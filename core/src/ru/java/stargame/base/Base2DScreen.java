package ru.java.stargame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.java.stargame.math.MatrixUtils;
import ru.java.stargame.math.Rect;

public class Base2DScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;

    protected Game game;
    private Rect screenBounds; //границы рисования в пикселях;
    private Rect worldBounds; //граница проэкции мировых координат;
    private Rect glBounds; //дефолтные границы OpenGL

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;
    protected Vector2 touch;

    public Base2DScreen(Game game) {
        this.game = game;
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBounds = new Rect(0,0,1f,1f);
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        this.touch = new Vector2();
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        screenBounds.setSize(width,height);
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);
        float aspect = width/height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f*aspect);

        MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);

        MatrixUtils.calcTransitionMatrix(screenToWorld, screenBounds,worldBounds);

        resize(worldBounds);
    }

    protected void resize(Rect worldBounds){

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDown(touch, pointer);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer) {

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchUp(touch, pointer);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDragged(touch, pointer);
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        mouseMoved(touch);
        return false;
    }

    public boolean mouseMoved(Vector2 touch) {
        return false;
    }


    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
