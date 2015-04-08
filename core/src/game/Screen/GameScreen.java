package game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.Camera.OrthoCamera;
import game.Entity.EntityManager;
import game.GameItems.ItemManager;
import game.Level.LevelAnimationManager;
import game.Level.LevelManager;
import game.TTmath;
import game.TextureManager;


public class GameScreen implements Screen{
	private TTmath game;
	private OrthoCamera camera;
	private SpriteBatch sb;
	private LevelManager levelManager;
	private LevelAnimationManager animationManager;
	private EntityManager entityManager;
	private ItemManager itemManager;
    private int levelNumber;
	
	public GameScreen(TTmath game, OrthoCamera camera, SpriteBatch sb, int levelNumber) {
		this.camera = camera;
		this.game = game;
		this.sb = sb;
        this.levelNumber = levelNumber;

		game.manageScreens(this);
		
//		levelManager = new LevelManager(TextureManager.mapTutorial);
        switch(levelNumber){
            case 0: levelManager = new LevelManager(TextureManager.mapTutorial);
                    break;
            case 1: levelManager = new LevelManager(TextureManager.Level1);
                    break;
            case 2: levelManager = new LevelManager(TextureManager.Level2);
                    break;
            case 3: levelManager = new LevelManager(TextureManager.Level3);
                    break;
            case 4: levelManager = new LevelManager(TextureManager.Level4);
                    break;
            case 5: levelManager = new LevelManager(TextureManager.Level5);
                    break;
            default: System.out.println("default level selector case");
        }

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
		
		// Only useful for desktop version
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			game.setScreen(new MenuScreen(game, camera, sb));
		}

		levelManager.renderMap(camera);
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		entityManager.render();
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