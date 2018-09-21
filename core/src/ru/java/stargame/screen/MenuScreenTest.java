package ru.java.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.java.stargame.base.Base2DScreen;

public class MenuScreenTest extends Base2DScreen {
    SpriteBatch batch;
    Texture img;
    Texture background;
    Vector2 posCur;
    Vector2 posEnd;
    Vector2 v;
    Vector2 vTemp;

    public MenuScreenTest(Game game){
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        background = new Texture("space_background.jpg");
        posCur = new Vector2(0f,0f);
        posEnd = new Vector2(posCur);
        v = new Vector2(0f,0f);
        vTemp = new Vector2(0f,0f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        vTemp.set(posCur);
        if (vTemp.sub(posEnd).len() <= 1) v.set(0,0);
        posCur.add(v);
        batch.begin();
        batch.draw(background,0, 0);
        batch.draw(img,posCur.x, posCur.y);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = Gdx.graphics.getHeight() - screenY;
        posEnd.set((float) screenX, (float) screenY);
        v = posEnd.cpy().sub(posCur).scl(0.011f);
        return true;
    }
}
