package game;

import game.Camera.OrthoCamera;
import game.Screen.ProblemScreen;
import game.Screen.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class TTmath extends Game{

	public static int WIDTH = 480, HEIGHT = 700;
	private OrthoCamera camera;
	private SpriteBatch sb;
//	public MenuScreen mainMenuScreen;
	public ProblemScreen problemScreen;
//	public GameScreen gameScreen;
//	public LevelMenuScreen levelMenu;
//	public GameOverScreen gameOver;
	public Screen previousScreen;
	public Screen currentScreen;

	private int incorrect = 0;
	private int correct = 0;
	private int counter = 0;
    private int rendCount;
    private long startTime;
    private long endTime;
	
	@Override
	public void create() {


        camera = new OrthoCamera();
		camera.resize();
		sb = new SpriteBatch();
//		mainMenuScreen = new MenuScreen(this, camera, sb);
//		setScreen(mainMenuScreen);
        startTime = TimeUtils.millis();
        setScreen(new SplashScreen(this, camera, sb));
	}
	
	public void clear(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public void manageScreens(Screen newCurrentScreen){
		if(currentScreen != null){
			previousScreen = currentScreen;
		}
		currentScreen = newCurrentScreen;
	}
	
	public void incorrectAns(){
		incorrect++;
	}
	
	public void resetAns(){
		incorrect = 0;
		correct = 0;
	}
	
	public int getIncorrect(){
		return incorrect;
	}
	
	public void correctAns(){
		correct++;
	}
	
	public int getcorrect(){
		return correct;
	}

	public void incrementCounter(){
		counter++;
	}
	
	public void resetCounter(){
		counter = 0;
	}
	
	public int getCounter(){
		return counter;
	}
	
    @Override
    public void dispose() {
        super.dispose();
        endTime = TimeUtils.millis();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
        rendCount++;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
