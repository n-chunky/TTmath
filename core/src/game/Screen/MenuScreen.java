package game.Screen;

import game.TTmath;
import game.Camera.OrthoCamera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
	private TextButton playButton, mentalMathButton;
	private TextButtonStyle textButtonStyle;
	private BitmapFont font;
	private Skin skin;
	private TextureAtlas buttonAtlas;


	// constructor to keep a reference to the main Game class
	public MenuScreen(TTmath game, OrthoCamera camera, SpriteBatch sb){
		this.camera = camera;
		this.game = game;
		this.sb = sb;

//		game.manageScreens(this);
		game.mainMenuScreen = this;
		
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
		textButtonStyle.up   = skin.getDrawable("MenuItem");


        // create play playButton
		playButton = new TextButton("Play Levels", textButtonStyle);
		playButton.pad(50);
		
		playButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelMenuScreen(game, camera, sb));
            }
        });




        // create mental math playButton
        mentalMathButton = new TextButton("Mental Math Practice", textButtonStyle);
        mentalMathButton.pad(20);

        mentalMathButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ProblemScreen(game, camera, sb, 0, 1));
            }
        });


		table.add(playButton);
        table.row();
        table.add(mentalMathButton);
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
