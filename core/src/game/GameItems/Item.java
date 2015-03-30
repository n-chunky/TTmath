package game.GameItems;

class Item {

	private String description;

	public Item(String desc) {
		description = desc;
	}

	public String getItem(){
		return description;
	}

	public boolean itemExists(){
		if(description!=null)
			return true;
		return false;
	}
}
