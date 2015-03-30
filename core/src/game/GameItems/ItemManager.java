package game.GameItems;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class ItemManager{

	static final int MAXITEMS = 3;
	ArrayList<Item> items = new ArrayList<Item>(3);

	public ItemManager(TiledMap map) {
		// TODO Auto-generated constructor stub
	}

	public void insertItem(String desc){
		Item item = new Item(desc);
		items.add(item);
	}
	
	public boolean checkItemExists(Item item){
		if(items.contains(item))
			return true;
		return false;
	}
	
}
