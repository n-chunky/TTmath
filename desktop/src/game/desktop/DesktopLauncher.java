package game.desktop;

import game.TTmath;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TTmath.WIDTH;
		config.height = TTmath.HEIGHT;
		new LwjglApplication(new TTmath(), config);
	}
}
