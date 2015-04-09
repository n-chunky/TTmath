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

	Array<StaticTiledMapTile> Tile;
	Texture tileGrass = TextureManager.GRASS;
	Texture tileStart = TextureManager.START;
	Texture tileEnd = TextureManager.END;
	TiledMap map;


	public LevelAnimationManager(TiledMap map){
		this.map = map;
		createL0Animation(tileGrass, "grass");
		createL1SmallAnimation(tileStart, "start");
		//createL1LargeAnimation(tileEnd, "end");
	}

	public void createL0Animation(Texture tileTexture, String tag){

		//hardcoded grass texture region
		Tile = new Array<StaticTiledMapTile>();

		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,0,0,32,32)));
		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,32,0,32,32)));
		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,64,0,32,32)));

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		for(int x = 0; x < layer.getWidth();x++){
			for(int y = 0; y < layer.getHeight();y++){
				TiledMapTileLayer.Cell cell = layer.getCell(x,y);
				Object property = cell.getTile().getProperties().get(tag);
				if(property != null){
					cell.setTile(new AnimatedTiledMapTile(1.5f,Tile));
				}
			}
		}
	}

	public void createL1SmallAnimation(Texture tileTexture, String tag){

		//hardcoded grass texture region
		Tile = new Array<StaticTiledMapTile>();

		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,0,0,32,32)));
		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,32,0,32,32)));
		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,64,0,32,32)));

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
		for(int x = 0; x < layer.getWidth();x++){
			for(int y = 0; y < layer.getHeight();y++){
				TiledMapTileLayer.Cell cell = layer.getCell(x,y);
				if(cell!=null && cell.getTile().getProperties().get(tag)!=null){
					cell.setTile(new AnimatedTiledMapTile(1.5f,Tile));
				}
			}
		}
	}
	
	public void createL1LargeAnimation(Texture tileTexture, String tag){

		//hardcoded grass texture region
		Tile = new Array<StaticTiledMapTile>();

		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,0,0,64,64)));
		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,64,0,64,64)));
		Tile.add(new StaticTiledMapTile(new TextureRegion(tileTexture,128,0,64,64)));

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
		for(int x = 0; x < layer.getWidth();x++){
			for(int y = 0; y < layer.getHeight();y++){
				TiledMapTileLayer.Cell cell = layer.getCell(x,y);
				if(cell!=null && cell.getTile().getProperties().get(tag)!=null){
					cell.setTile(new AnimatedTiledMapTile(1.5f,Tile));
				}
			}
		}
	}
	
}



