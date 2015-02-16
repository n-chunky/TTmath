package game.Level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public abstract class Level {

	TiledMap map;
	TiledMapRenderer render;
	
	public Level(String mapFileName){

		map = new TmxMapLoader().load(mapFileName);
		render = new OrthogonalTiledMapRenderer(map);
		
	}
	
	public abstract void LoadMap();
	public abstract void setView();
	
	
}
