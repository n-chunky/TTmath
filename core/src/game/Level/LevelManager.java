package game.Level;

import game.Camera.OrthoCamera;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class LevelManager{
;
	TiledMap map;
	TiledMapRenderer render;

	public LevelManager(TiledMap map) {
		this.map = map;
	}

	public void createMap(){
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
