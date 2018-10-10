package ru.java.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.java.stargame.base.ActionListener;
import ru.java.stargame.base.ScaledTouchUpButton;


public class ButtonNewGame extends ScaledTouchUpButton {

    public ButtonNewGame(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("button_new_game"), actionListener, 0.9f);
        setHeightProportion(0.05f);
        setTop(-0.012f);
    }
}
