package ru.java.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

import ru.java.stargame.base.Base2DScreen;
import ru.java.stargame.base.Sprite;

public class MenuScreen extends Base2DScreen {

    Sprite spr_btnExit;
    Texture img;
    Texture background;
    Vector2 posCur;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch.getProjectionMatrix().idt();
        img = new Texture("badlogic.jpg");
        background = new Texture("space_background.jpg");
        posCur = new Vector2(0f,0f);
        TextureRegion btnExit[] = new TextureRegion[2];
        btnExit[0] = new TextureRegion(new Texture(Gdx.files.internal("exit0_8.png")));
        btnExit[1] = new TextureRegion(new Texture(Gdx.files.internal("exit1_8.png")));
        spr_btnExit = new Sprite(btnExit);
        spr_btnExit.setHeightProportion(0.1f);
        spr_btnExit.setLeft(-0.15f);
        spr_btnExit.setBottom(-0.1f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,-0.5f, -0.5f,2f,2f);
       // batch.draw(img,-0.5f, -0.5f,0.2f,0.3f);
        spr_btnExit.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean mouseMoved(Vector2 touch) {
        if (spr_btnExit.isMe(touch)) {
            spr_btnExit.setFrame(1);
        }
        else {
            spr_btnExit.setFrame(0);
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (spr_btnExit.isMe(touch)) {
            System.exit(-1);
        }
        return false;
    }
}
