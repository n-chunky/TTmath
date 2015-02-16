package game;

import game.Entity.Player1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class t2 extends ApplicationAdapter implements InputProcessor {
	Texture img;
	private TiledMap tiledMap;
	private OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;
	private Batch sb;
	private Texture texture;
	Sprite sprite;
	
	private Player1 player;
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		tiledMap = new TmxMapLoader().load("map1Test/2.tmx");
		renderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);
		
		// set sprite
		sb = new SpriteBatch();
		player = new Player1(new Sprite(new Texture(Gdx.files.internal("resources/mainC.png"))));
		//player.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		player.setPosition(camera.position.x, camera.position.y);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		renderer.setView(camera);
		renderer.render();

		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		player.draw(sb);
		sb.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.LEFT){
			player.setX(player.getX()-32);
			camera.position.set(player.getX(), player.getY(), 0);
			camera.update();
		}
		if(keycode == Input.Keys.RIGHT){
			player.setX(player.getX()+32);
			camera.position.set(player.getX(), player.getY(), 0);
			camera.update();
		}
		if(keycode == Input.Keys.UP){
			player.setY(player.getY()+32);
			camera.position.set(player.getX(), player.getY(), 0);
			camera.update();
		}
		if(keycode == Input.Keys.DOWN){
			player.setY(player.getY()-32);
			camera.position.set(player.getX(), player.getY(), 0);
			camera.update();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//		Vector3 clickCoordinates = new Vector3(screenX, screenY, 0);
//		Vector3 position = camera.unproject(clickCoordinates);
//		sprite.setPosition(position.x, position.y);
//		return true;
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
