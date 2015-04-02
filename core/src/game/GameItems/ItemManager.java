package game.GameItems;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class ItemManager{

	static final int MAXITEMS = 3;
	ArrayList<String> items = new ArrayList<String>();

	public ItemManager(TiledMap map) {
		// TODO Auto-generated constructor stub
	}

	public void insertItem(String desc){
		items.add(desc);
	}
	
	public boolean checkItemExists(String desc){
		if(items.contains(desc))
			return true;
		return false;
	}

	public void removeItem(String desc){
		if(checkItemExists(desc)){
			items.remove(desc);
		}
	}
	
}
