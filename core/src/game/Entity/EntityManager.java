package game.Entity;

import game.TTmath;
import game.Camera.OrthoCamera;
import game.GameItems.ItemManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EntityManager {

	private final Array<Entity> entities = new Array<Entity>();
	private Player player;
	TiledMap map;
	TiledMapTileLayer tiles;
	SpriteBatch sb;
	
	public EntityManager(int amount, OrthoCamera camera, TiledMap tiledMap, TTmath game, SpriteBatch sb, ItemManager itemManager){
		map = tiledMap;
		tiles = (TiledMapTileLayer) map.getLayers().get(1);
		this.sb = sb;
		player = new Player(findStartPosition(), new Vector2(0,0), camera, (TiledMapTileLayer) tiles, game, sb, itemManager);
		setInputProcessor();
	}
	
	public void setInputProcessor(){
		Gdx.input.setInputProcessor(player);
	}
	
	public void update(){
		for(Entity entity : entities){
			entity.update();
		}
//		if(player.)
		player.update();
	}
	
	public void render(){
		for(Entity entity : entities){
			entity.render(sb);;
		}
		player.render(sb);
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}

//	
//	public void setEntityPosition(){
//		
//	}
//	
//	public Vector2 getEntityPosition(){
//		
//		return new Vector2();
//	}
	
	//maybe temporary
	//finds the start tile for the player, not entity
	public Vector2 findStartPosition(){

		Vector2 start = null;
		Cell temp;
		//tile width and height = 32, so multiple width and height to get the area
		//add stepx and stepy by 32 to move between each tile
		for(int y = 0; y < tiles.getHeight(); y++){
			for(int x = 0; x < tiles.getWidth(); x++){
				temp = tiles.getCell(x, y);
				if( temp != null && temp.getTile().getProperties().containsKey("start")){
					start = new Vector2((x * tiles.getTileWidth()), (y * tiles.getTileHeight()));
					break;
				}
			}
			if(start != null){
				break;
			}
		}
		if(start == null){
			System.out.println("could not find start position");
		}
		return start;
	}
	
	public boolean playerEnd(){
		if(player.endReach()){
			return true;
		}
		return false;
	}
	
}