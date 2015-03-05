package game.Screen;

import game.TextureManager;
import game.Camera.OrthoCamera;
import game.Entity.EntityManager;
import game.Level.LevelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class GameScreen extends Screen{

	private OrthoCamera camera;
	private EntityManager entityManager;
	private LevelManager levelManager;
	private Touchpad touch;
	private Skin touchSkin;
	private TouchpadStyle touchStyle;
	private Drawable touchBackground;
	private Drawable touchKnob;
	public GameScreen(OrthoCamera cam){
		camera = cam;
	}

	@Override
	public void create() {
		SpriteBatch batch = new SpriteBatch();
		//create touchpad
		touchSkin = new Skin();
		touchSkin.add("touchBackground", TextureManager.TOUCHPADBACKGROUND);
		touchSkin.add("touchKnob", TextureManager.TOUCHPADKNOB);
		touchStyle = new TouchpadStyle();
		touchBackground = touchSkin.getDrawable("touchBackground");
		touchKnob = touchSkin.getDrawable("touchKnob");
		touchStyle.background = touchBackground;
		touchStyle.knob = touchKnob;
		touch = new Touchpad(10, touchStyle);
		touch.setBounds(10, 10, 200, 200);
		
		Stage stage = new Stage();
		stage.addActor(touch);			
		Gdx.input.setInputProcessor(stage);
		
		levelManager = new LevelManager("map1Test/level1.tmx");
		levelManager.createMap();
		entityManager = new EntityManager(2, camera, levelManager.getMap(), touch);
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
