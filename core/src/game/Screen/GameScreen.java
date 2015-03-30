package game.Screen;

import game.TTmath;
import game.TextureManager;
import game.Camera.OrthoCamera;
import game.Entity.EntityManager;
import game.GameItems.ItemManager;
import game.Level.LevelAnimationManager;
import game.Level.LevelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen{
	private TTmath game;
	private OrthoCamera camera;
	private SpriteBatch sb;
	private LevelManager levelManager;
	private LevelAnimationManager animationManager;
	private EntityManager entityManager;
	private ItemManager itemManager;
	
	public GameScreen(TTmath game, OrthoCamera camera, SpriteBatch sb) {
		this.camera = camera;
		this.game = game;
		this.sb = sb;

		levelManager = new LevelManager(TextureManager.mapTutorial);
		animationManager = new LevelAnimationManager(levelManager.getMap());
		itemManager = new ItemManager(levelManager.getMap());
		entityManager = new EntityManager(2, camera, levelManager.getMap(), game, sb, itemManager);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		game.clear();
		levelManager.createMap();
		camera.update();
		entityManager.update();
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			game.setScreen(new MenuScreen(game, camera, sb));
			game.setPreviousScreen(this);
		}

		levelManager.renderMap(camera);
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		entityManager.render(sb);
		sb.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}