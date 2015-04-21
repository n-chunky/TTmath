package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class TextureManager {

	public static Texture PLAYER            = new Texture(Gdx.files.internal("Entities/PlayerSpriteSheet.png"));
	public static Texture MENUSCREEN        = new Texture(Gdx.files.internal("Menus/MenuItem.png"));
	public static Texture PROBLEM           = new Texture(Gdx.files.internal("resources/mathExample.png"));
	public static TiledMap mapTutorial      = new TmxMapLoader().load("MapsObjects/tutorial.tmx");
	public static TiledMap Level1           = new TmxMapLoader().load("MapsObjects/Path1Level1.tmx");
	public static TiledMap Level2           = new TmxMapLoader().load("MapsObjects/Path1Level2.tmx");
	public static TiledMap Level3           = new TmxMapLoader().load("MapsObjects/Path1Level3.tmx");
	public static TiledMap Level4           = new TmxMapLoader().load("MapsObjects/Path1Level4.tmx");
	public static TiledMap Level5           = new TmxMapLoader().load("MapsObjects/Path1Level5.tmx");
	public static Texture GRASS             = new Texture(Gdx.files.internal("MapsObjects/grass.png"));
	public static Texture KEY               = new Texture(Gdx.files.internal("MapsObjects/Key.png"));
	public static Texture DOOROPEN          = new Texture(Gdx.files.internal("MapsObjects/DoorOpen.png"));
	public static Texture SPECIALDOOROPEN   = new Texture(Gdx.files.internal("MapsObjects/SpecialDoorOpen.png"));
	public static Texture GAMEBUTTON        = new Texture(Gdx.files.internal("Menus/gameMenuButton.png"));
	public static Texture START             = new Texture(Gdx.files.internal("MapsObjects/StartWarpgate.png"));
	public static Texture END               = new Texture(Gdx.files.internal("MapsObjects/Warpgate.png"));
    public static Texture SPLASH            = new Texture(Gdx.files.internal("resources/SplashScreen.png"));
    
	public static void resetMaps(){
		Level1 = new TmxMapLoader().load("MapsObjects/Path1Level1.tmx");
		Level2 = new TmxMapLoader().load("MapsObjects/Path1Level2.tmx");
		Level3 = new TmxMapLoader().load("MapsObjects/Path1Level3.tmx");
		Level4 = new TmxMapLoader().load("MapsObjects/Path1Level4.tmx");
		Level5 = new TmxMapLoader().load("MapsObjects/Path1Level5.tmx");
	}
}