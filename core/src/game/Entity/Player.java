package game.Entity;

import game.TextureManager;
import game.Camera.OrthoCamera;
import game.Screen.ProblemScreen;
import game.Screen.ScreenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Player extends Entity implements InputProcessor{
	private OrthoCamera camera;
	private TiledMapTileLayer collisionLayer;
	InputListener listener = new InputListener();
	static float playerHeight;
	static float playerWidth;
	static float tileWidth;
	static float tileHeight;
	boolean movingRight = false;
	boolean movingLeft = false;
	boolean movingUp = false;
	boolean movingDown = false;
	boolean isMoving = false;

	//tags for tiles
	//blocked
	//start
	//end
	//math

	public Player(Vector2 pos, Vector2 direction, OrthoCamera camera, TiledMapTileLayer collLayer) {
		super(TextureManager.PLAYER, pos, direction);
		this.camera = camera;
		this.collisionLayer = collLayer;
		tileWidth = collisionLayer.getTileWidth();
		tileHeight = collisionLayer.getTileHeight();
		setTextureRegion(0, 0, 32, 32);
		playerHeight = 30;
		playerWidth = 30;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void update() {
		pos.add(direction);

		//				if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && !collisionExist(Keys.DPAD_LEFT)){
		//					setDirection(-300,0);
		//				}
		//				else if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && !collisionExist(Keys.DPAD_RIGHT)){
		//					setDirection(300,0);
		//				}
		//				else if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN) && !collisionExist(Keys.DPAD_DOWN)){
		//					setDirection(0,-300);
		//				}
		//				else if(Gdx.input.isKeyPressed(Keys.DPAD_UP) && !collisionExist(Keys.DPAD_UP)){
		//					setDirection(0,300);
		//				}
		//				else{
		//					setDirection(0, 0);
		//					camera.setPosition(pos.x, pos.y);
		//					camera.update();
		//				}

		if(isMoving){
			if(movingDown && collidesBottom()){
				movingDown = false;
				setDirection(0,0);
			}
			if(movingUp && collidesTop()){
				movingUp = false;
				setDirection(0,0);
			}
			if(movingRight && collidesRight()){
				movingRight = false;
				setDirection(0,0);
			}
			if(movingLeft && collidesLeft()){
				movingLeft = false;
				setDirection(0,0);
			}
			isMoving = false;
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

	//	//checks if collision exists around the player
	//	private boolean collisionExist(int key){
	//
	//		boolean collision = false;
	//
	//		//left
	//		if(key == Keys.DPAD_LEFT)
	//			collision = collidesLeft();
	//
	//		//right
	//		if(key == Keys.DPAD_RIGHT)
	//			collision = collidesRight();
	//
	//		//up
	//		if(key == Keys.DPAD_UP)
	//			collision = collidesTop();
	//
	//		//down
	//		if(key == Keys.DPAD_DOWN)
	//			collision = collidesBottom();
	//
	//		return collision;
	//	}

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

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		isMoving = true; 

		if(Gdx.input.getDeltaY()>10 && !collidesBottom()){
			movingDown = true;
			setDirection(0,-250);
		}
		else if(Gdx.input.getDeltaY()<-10 && !collidesTop()){
			movingUp = true;
			setDirection(0,250);
		}
		else if(Gdx.input.getDeltaX()>10 && !collidesRight()){
			movingRight = true;
			setDirection(250,0);
		}
		else if(Gdx.input.getDeltaX()<-10 && !collidesLeft()){
			movingLeft = true;
			setDirection(-250,0);
		}

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//		isMoving = true; 
//
//		System.out.println("This is the y: " + Gdx.input.getY());
//		System.out.println("This is the x: " + Gdx.input.getX());
//		if(Gdx.input.getY()>60 && !collidesTop()){
//			setDirection(0,-250);
//			movingUp = true;
//		}
//		else if(Gdx.input.getDeltaY()<-10 && !collidesBottom()){
//			setDirection(0,250);
//			movingDown = true;
//		}
//		else if(Gdx.input.getDeltaX()>10 && !collidesRight()){
//			setDirection(250,0);
//			movingRight = true;
//		}
//		else if(Gdx.input.getDeltaX()<-10 && !collidesLeft()){
//			setDirection(-250,0);
//			movingLeft = true;
//		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		setDirection(0, 0);
		return false;
	}

	//below methods will not be used

	//not used
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	//not used
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	//not used
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	//not used
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	//not used
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
