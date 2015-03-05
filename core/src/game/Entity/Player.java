package game.Entity;

import game.TextureManager;
import game.Camera.OrthoCamera;
import game.Screen.ProblemScreen;
import game.Screen.ScreenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class Player extends Entity{
	private OrthoCamera camera;
	private TextureRegion s;
	private TiledMapTileLayer collisionLayer;
	private Touchpad touch;
	static float playerHeight;
	static float playerWidth;
	static float tileWidth;
	static float tileHeight;
	
	//tags for tiles
	//blocked
	//start
	//end
	//math

	public Player(Vector2 pos, Vector2 direction, OrthoCamera camera, TiledMapTileLayer collLayer, Touchpad touch) {
		super(TextureManager.PLAYER, pos, direction);
		this.camera = camera;
		this.collisionLayer = collLayer;
		this.touch = touch;
		tileWidth = collisionLayer.getTileWidth();
		tileHeight = collisionLayer.getTileHeight();
		setTextureRegion(16, 651, 30, 52);
		playerHeight = 30;
		playerWidth = 30;
		//16,651
	}

	@Override
	public void update() {
		pos.add(direction);
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && !collisionExist(Keys.DPAD_LEFT)){
			setDirection(-300,0);
		}
		else if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && !collisionExist(Keys.DPAD_RIGHT)){
			setDirection(300,0);
		}
		else if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN) && !collisionExist(Keys.DPAD_DOWN)){
			setDirection(0,-300);
		}
		else if(Gdx.input.isKeyPressed(Keys.DPAD_UP) && !collisionExist(Keys.DPAD_UP)){
			setDirection(0,300);
		}
		else{
			setDirection(0, 0);
			camera.setPosition(pos.x, pos.y);
			camera.update();
		}
		camera.setPosition(pos.x, pos.y);
		camera.update();
		if(endReach()){
			System.out.println("U REACHED THE END");
			ScreenManager.setScreen(new ProblemScreen(camera));
		}
	}

	//checks the current tile of the player to see if he reached the end or not
	private boolean endReach() {
		int tileX = (int) (this.getPosition().x / collisionLayer.getTileWidth());
		int tileY = (int) (this.getPosition().y / collisionLayer.getTileHeight());
		Cell cell = collisionLayer.getCell(tileX, tileY);
		return cell != null && cell.getTile().getProperties().containsKey("end");
	}

	//checks if collision exists around the player
	private boolean collisionExist(int key){

		boolean collision = false;

		//left
		if(key == Keys.DPAD_LEFT)
			collision = collidesLeft();

		//right
		if(key == Keys.DPAD_RIGHT)
			collision = collidesRight();

		//up
		if(key == Keys.DPAD_UP)
			collision = collidesTop();

		//down
		if(key == Keys.DPAD_DOWN)
			collision = collidesBottom();

		return collision;
	}

	/*
	 * collision check
	 * added +3y more for tile detection right and left.
	 * added +5x more for tile detection top and bottom
	 * added -2y for tile detection bottom
	 * for making collision detection better
	 */
	public boolean collidesRight() {
		for(float step = 0; step < playerHeight; step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked((this.getPosition().x+ + playerWidth) / tileWidth, ((this.getPosition().y + step)+3) / tileHeight))
				return true;
		return false;
	}

	public boolean collidesLeft() {
		for(float step = 0; step < playerHeight; step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked((this.getPosition().x) / tileWidth, ((this.getPosition().y + step)+3) / tileHeight))
				return true;
		return false;
	}

	public boolean collidesTop() {
		for(float step = 0; step < playerWidth; step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(((this.getPosition().x + step)+5) / tileWidth, ((this.getPosition().y + playerHeight)) / tileHeight))
				return true;
		return false;
	}

	public boolean collidesBottom() {
		for(float step = 0; step < playerWidth; step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(((this.getPosition().x + step)+5) / tileWidth, ((this.getPosition().y)-2) / tileHeight))
				return true;
		return false;
	}

	//checks if the cell contains tag "blocked"
	private boolean isCellBlocked(float x, float y){
		Cell cell = collisionLayer.getCell((int) x, (int) y);
		return cell !=null && cell.getTile().getProperties().containsKey("blocked");
	}

}
