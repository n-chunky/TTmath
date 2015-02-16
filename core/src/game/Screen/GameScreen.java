package game.Screen;

import game.Camera.OrthoCamera;
import game.Entity.EntityManager;
import game.Level.LevelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends Screen{

	private OrthoCamera camera;
	private EntityManager entityManager;
	private LevelManager levelManager;

	public GameScreen(OrthoCamera cam){
		camera = cam;
	}

	@Override
	public void create() {

		levelManager = new LevelManager("map1Test/level1.tmx");
		levelManager.createMap();
		entityManager = new EntityManager(2, camera, levelManager.getMap());

	}

	@Override
	public void update() {
		camera.update();
		entityManager.update();
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			ScreenManager.setScreen(new MenuScreen(camera));
		}
	}

	@Override
	public void render(SpriteBatch sb) {


		levelManager.renderMap(camera);
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		entityManager.render(sb);
		sb.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.resize();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

}
