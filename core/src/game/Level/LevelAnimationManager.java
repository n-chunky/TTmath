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
	Array<StaticTiledMapTile> Tile;
	Texture tileTexture = TextureManager.GRASS;
	TiledMap map;
	
	
	public LevelAnimationManager(TiledMap map){
		this.map = map;
		createAnimation(map.getTileSets().getTileSet("grass"));
		
	}
	
	public void createAnimation(TiledMapTileSet tileSet){
		
		//hardcoded grass texture region
		Tile = new Array<StaticTiledMapTile>();
		
		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,0,0,32,32)));
		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,32,0,32,32)));

		grassTileInScene = new Array<AnimatedTiledMapTile>();

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		for(int x = 0; x < layer.getWidth();x++){
			for(int y = 0; y < layer.getHeight();y++){
				TiledMapTileLayer.Cell cell = layer.getCell(x,y);
				Object property = cell.getTile().getProperties().get("grass");
				if(property != null){
					cell.setTile(new AnimatedTiledMapTile(1.5f,Tile));
				}
			}
		}
	}
	
}



