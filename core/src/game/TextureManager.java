package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class TextureManager {

	public static Texture PLAYER = new Texture(Gdx.files.internal("Entities/PlayerSpriteSheet.png"));
	public static Texture MENUSCREEN = new Texture(Gdx.files.internal("Menus/MenuItem.png"));
	public static Texture PROBLEM = new Texture(Gdx.files.internal("resources/mathExample.png"));
	public static TiledMap mapTutorial = new TmxMapLoader().load("MapsObjects/tutorial.tmx");
	public static TiledMap Path1Level1 = new TmxMapLoader().load("MapsObjects/Path1Level1.tmx");
	public static TiledMap Path1Level2 = new TmxMapLoader().load("MapsObjects/Path1Level2.tmx");
	public static TiledMap Path1Level3 = new TmxMapLoader().load("MapsObjects/Path1Level3.tmx");
	public static TiledMap Path1Level4 = new TmxMapLoader().load("MapsObjects/Path1Level4.tmx");
	public static TiledMap Path1Level5 = new TmxMapLoader().load("MapsObjects/Path1Level5.tmx");
	public static Texture GRASS = new Texture(Gdx.files.internal("MapsObjects/grass.png"));
	public static Texture KEY = new Texture(Gdx.files.internal("MapsObjects/key.png"));
}