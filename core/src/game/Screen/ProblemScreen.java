package game.Screen;

import game.TTmath;
import game.TextureManager;
import game.Camera.OrthoCamera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ProblemScreen extends Screen{
	
	private OrthoCamera camera;
	private Texture problem;
	
	public ProblemScreen(OrthoCamera camera) {
		this.camera = camera;
		problem = TextureManager.PROBLEM;
	}

	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
	}

	@Override
	public void update() {
		camera.update();
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			ScreenManager.setScreen(new MenuScreen(camera));
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(problem, TTmath.WIDTH / 2 - problem.getWidth() / 2, TTmath.HEIGHT / 2 - problem.getHeight() / 2);
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
