package game.Level;

import game.TextureManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

public class LevelAnimationManager {

	Array<AnimatedTiledMapTile> grassTileInScene;
	Array<StaticTiledMapTile> grassTile;
	Texture grass = TextureManager.GRASS;
	
	public LevelAnimationManager(TiledMap map){

		TiledMapTileSet tileSet = map.getTileSets().getTileSet("grass");
		grassTile = new Array<StaticTiledMapTile>();

		//hardcoded grass texture region
		grassTile.add(new StaticTiledMapTile(new TextureRegion(grass,0,0,32,32)));
		grassTile.add(new StaticTiledMapTile(new TextureRegion(grass,32,0,32,32)));

		grassTileInScene = new Array<AnimatedTiledMapTile>();

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		for(int x = 0; x < layer.getWidth();x++){
			for(int y = 0; y < layer.getHeight();y++){
				TiledMapTileLayer.Cell cell = layer.getCell(x,y);
				Object property = cell.getTile().getProperties().get("grass");
				if(property != null){
					cell.setTile(new AnimatedTiledMapTile(1.5f,grassTile));
				}
			}
		}

	}
}