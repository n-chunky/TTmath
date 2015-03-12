package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class TextureManager {

	public static Texture PLAYER = new Texture(Gdx.files.internal("Entities/PlayerSpriteSheet.png"));
	public static Texture MENUSCREEN = new Texture(Gdx.files.internal("Menus/MenuItem.png"));
	public static Texture PROBLEM = new Texture(Gdx.files.internal("resources/mathExample.png"));
	public static TiledMap mapTutorial = new TmxMapLoader().load("map1Test/level1.tmx");
	public static TiledMap Path1Level1 = new TmxMapLoader().load("map1Test/level1.tmx");
	public static TiledMap Path1Level2 = new TmxMapLoader().load("map1Test/level1.tmx");
	public static TiledMap Path1Level3 = new TmxMapLoader().load("map1Test/level1.tmx");
	public static TiledMap Path1Level4 = new TmxMapLoader().load("map1Test/level1.tmx");
	public static TiledMap Path1Level5 = new TmxMapLoader().load("map1Test/level1.tmx");
}