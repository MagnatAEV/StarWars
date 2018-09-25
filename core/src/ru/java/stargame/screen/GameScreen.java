package ru.java.stargame.screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.java.stargame.base.Base2DScreen;
import ru.java.stargame.math.Rect;
import ru.java.stargame.sprites.Background;
import ru.java.stargame.sprites.BattleCruser;
import ru.java.stargame.sprites.Star;


public class GameScreen extends Base2DScreen {

    private static final int STAR_COUNT = 64;

    Background background;
    Texture bg;
    TextureAtlas atlas;

    BattleCruser battleCruser;

    Star[] star;

    public GameScreen(Game game) {

        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("space_background.jpg");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlas);
        }
        TextureRegion region = new TextureRegion(atlas.findRegion("main_ship"));
        int halfWidth = region.getRegionWidth()/2;
        int height = region.getRegionHeight();
        region = new TextureRegion(region, 0,0, halfWidth, height);
        battleCruser = new BattleCruser(atlas, region, 0.2f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
        battleCruser.update();
    }

    public void checkCollisions() {

    }

    public void deleteAllDestroyed() {

    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0.4f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        battleCruser.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);

        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        battleCruser.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        battleCruser.touchDown(touch, pointer);
        return true;
    }


    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        battleCruser.touchUp(touch, pointer);
        return true;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        battleCruser.touchDragged(touch, pointer);
        return true;
    }
}
