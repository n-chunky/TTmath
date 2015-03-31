package game.Screen;

import game.TTmath;
import game.TextureManager;
import game.Camera.OrthoCamera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MenuScreen implements Screen{

	private TTmath game;
	private SpriteBatch sb;
	private OrthoCamera camera;

	private Table table;
	private Stage stage;
	private TextButton button;
	private TextButtonStyle textButtonStyle;
	private BitmapFont font;
	private Skin skin;
	private TextureAtlas buttonAtlas;


	// constructor to keep a reference to the main Game class
	public MenuScreen(TTmath game, OrthoCamera camera, SpriteBatch sb){
		this.camera = camera;
		this.game = game;
		this.sb = sb;

		game.manageScreens(this);
		
		createStage();

		FreeTypeFontGenerator openSans = new FreeTypeFontGenerator(Gdx.files.internal("resources/OpenSans-Regular.ttf"));
		font = createFont(openSans, 40);
		openSans.dispose();

		createButton();
	}

	private void createStage(){
		stage = new Stage();
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(stage);
	}

	private void createButton(){
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("Menus/buton.pack"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("MenuItem");
		button = new TextButton("Touch me", textButtonStyle);
		button.pad(20);
		
		button.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(game.previousScreen != null){
					// This is how to go back a screen!
//					game.setScreen(game.previousScreen);
//					dispose();
				}
				else{
					// Commented out for testing purposes
//					game.setScreen(new GameScreen(game, camera, sb));
//					game.setScreen(new ProblemScreen(game, camera, sb));
				}
				game.setScreen(new GameScreen(game, camera, sb));
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				// Do nothing?
			}
		});
		
		table.add(button);

		stage.addActor(table);
	}

	private BitmapFont createFont(FreeTypeFontGenerator ftfg, float dp){
		return ftfg.generateFont((int)(dp * Gdx.graphics.getDensity()));
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
		
		stage.draw();
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
