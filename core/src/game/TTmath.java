package game;

import game.Camera.OrthoCamera;
import game.Screen.GameOverScreen;
import game.Screen.GameScreen;
import game.Screen.LevelMenuScreen;
import game.Screen.MenuScreen;
import game.Screen.ProblemScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TTmath extends Game{

	public static int WIDTH = 480, HEIGHT = 700;
	private OrthoCamera camera;
	private SpriteBatch sb;
	public MenuScreen mainMenuScreen;
	public ProblemScreen problemScreen;
	public GameScreen gameScreen;
	public LevelMenuScreen levelMenu;
	public GameOverScreen gameOver;
//	public Screen previousScreen;
//	public Screen currentScreen;
	private int incorrect = 0;
	private int correct = 0;
	
	@Override
	public void create() {
		camera = new OrthoCamera();
		camera.resize();
		sb = new SpriteBatch();
		mainMenuScreen = new MenuScreen(this, camera, sb);
		setScreen(mainMenuScreen);              
	}
	
	public void clear(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
//	public void manageScreens(Screen newCurrentScreen){
//		if(currentScreen != null){
//			previousScreen = currentScreen;
//		}
//		currentScreen = newCurrentScreen;
//	}
	
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
}
