package game.Screen;

import game.TTmath;
import game.TextureManager;
import game.Camera.OrthoCamera;
import game.Entity.EntityManager;
import game.GameItems.ItemManager;
import game.Level.LevelAnimationManager;
import game.Level.LevelManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;


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

        Gdx.input.setCatchBackKey(true);
		game.manageScreens(this);
//        game.gameScreen = this;

		TextureManager.resetMaps();
		TiledMap map;
        // choose the map
        switch(levelNumber){
            case 0:
            	map = TextureManager.mapTutorial;
            	levelManager = new LevelManager(map);
                    break;
            case 1: 
            	map = TextureManager.Level1;
            	levelManager = new LevelManager(map);
                    break;
            case 2: 
            	map = TextureManager.Level2;
            	levelManager = new LevelManager(map);
                    break;
            case 3: 
            	map = TextureManager.Level3;
            	levelManager = new LevelManager(map);
                    break;
            case 4: 
            	map = TextureManager.Level4;
            	levelManager = new LevelManager(map);
                    break;
            case 5: 
            	map = TextureManager.Level5;
            	levelManager = new LevelManager(map);
                    break;
            default: System.out.println("default level selector case");
                    break;
        }

		levelManager.createMap();
		itemManager = new ItemManager(levelManager.getMap());
		entityManager = new EntityManager(2, camera, levelManager.getMap(), game, sb, itemManager);
		animationManager = new LevelAnimationManager(levelManager.getMap());
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		game.clear();
		camera.update();
		
		levelManager.renderMap(camera);
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		entityManager.render();
		entityManager.update();
		sb.end();


        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new LevelMenuScreen(game, camera, sb));
            game.currentScreen.dispose();
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
		this.dispose();
	}

}