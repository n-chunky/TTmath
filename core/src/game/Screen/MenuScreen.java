package game.Screen;

import game.TTmath;
import game.TextureManager;
import game.Camera.OrthoCamera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen{
	
	private TTmath game;
	private SpriteBatch sb;
	private OrthoCamera camera;
	private Texture Menu;

	// constructor to keep a reference to the main Game class
    public MenuScreen(TTmath game, OrthoCamera camera, SpriteBatch sb){
		Menu = TextureManager.MENUSCREEN;
		this.camera = camera;
    	this.game = game;
		this.sb = sb;
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		game.clear();

		camera.setPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(Menu, camera.position.x - Menu.getWidth() / 2, camera.position.y - Menu.getHeight() / 2);
		sb.end();

		
		if(Gdx.input.isTouched()){
			if(game.previousScreen != null){
				game.setScreen(game.previousScreen);
			}
			else{
				game.setScreen(new GameScreen(game, camera, sb));
			}
//			game.setScreen(new ProblemScreen(game, camera, sb));
			this.dispose();
		}
		
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
