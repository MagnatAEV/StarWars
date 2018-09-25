package ru.java.stargame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.java.stargame.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		float aspect = 3f/4f;
		config.resizable = false;
		config.width = 300;
		config.height = (int) (config.width/aspect);
		new LwjglApplication(new StarGame(), config);
	}
}
