package game.Screen;

import game.TTmath;
import game.TextureManager;
import game.Camera.OrthoCamera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen extends Screen{
	
	private OrthoCamera camera;
	private Texture Menu;

	public MenuScreen(OrthoCamera camera){
		Menu = TextureManager.MENUSCREEN;
		this.camera = camera;
	}
	
	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
	}

	@Override
	public void update() {
		camera.update();
		
		if(Gdx.input.isTouched()){
			ScreenManager.setScreen(new GameScreen(camera));
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(Menu, TTmath.WIDTH / 2 - Menu.getWidth() / 2, TTmath.HEIGHT / 2 - Menu.getHeight() / 2);
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
