package ru.java.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.java.stargame.base.ActionListener;
import ru.java.stargame.base.ScaledTouchUpButton;
import ru.java.stargame.math.Rect;


public class ButtonPlay extends ScaledTouchUpButton {

    public ButtonPlay(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("btPlay"), actionListener, 0.9f);
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
