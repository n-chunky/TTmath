package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {

	public static Texture PLAYER = new Texture(Gdx.files.internal("Entities/SaraFullSheet.png"));
	public static Texture MENUSCREEN = new Texture(Gdx.files.internal("Menus/MenuItem.png"));
	public static Texture PROBLEM = new Texture(Gdx.files.internal("resources/mathExample.png"));
	public static Texture TOUCHPADBACKGROUND = new Texture(Gdx.files.internal("Touchpad/background.png"));
	public static Texture TOUCHPADKNOB = new Texture(Gdx.files.internal("Touchpad/knob.png"));
}