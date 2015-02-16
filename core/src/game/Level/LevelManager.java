package game.Level;

import game.Camera.OrthoCamera;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class LevelManager{

	String mapFileName;
	TiledMap map;
	TiledMapRenderer render;

	public LevelManager(String mapFileName) {
		this.mapFileName = mapFileName;
	}

	public void createMap(){
		map = new TmxMapLoader().load(mapFileName);	
		render = new OrthogonalTiledMapRenderer(map);
	}
	
	public void renderMap(OrthoCamera camera){
		render.setView(camera);
		render.render();
	}

	public TiledMap getMap() {

		return map;
	}
	
	
	
}
