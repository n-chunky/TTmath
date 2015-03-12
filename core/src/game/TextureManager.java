package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class TextureManager {

	public static Texture PLAYER = new Texture(Gdx.files.internal("Entities/PlayerSpriteSheet.png"));
	public static Texture MENUSCREEN = new Texture(Gdx.files.internal("Menus/MenuItem.png"));
	public static Texture PROBLEM = new Texture(Gdx.files.internal("resources/mathExample.png"));
}