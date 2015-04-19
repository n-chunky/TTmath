package game.Screen;


import game.TTmath;
import game.Camera.OrthoCamera;
import game.MathAlgorithms.mathQCreator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class ProblemScreen implements Screen{
	private BitmapFont text;
	private OrthoCamera camera;
	private TTmath game;
	private int gamemode;
	private int level;
	private int[][] answers;
	private String[] questions;
	private Label label;
	private Stage stage;
	private Table table;
	private Skin skin;
	private TextButton buttons[];
	private mathQCreator math;
	private InputProcessor previousProcessor;
	private boolean created;
	SpriteBatch sb;


	//int path should be added later
	public ProblemScreen(TTmath game, OrthoCamera camera, SpriteBatch sb, int gamemode, int level){
		this.camera = camera;
		this.game = game;
		this.sb = sb;
		this.gamemode = gamemode;
		this.level = level;
		this.previousProcessor = Gdx.input.getInputProcessor();

		createDisplay();
	}

	private void createDisplay() {
		if(created){
			return;
		}

		FreeTypeFontGenerator openSans = new FreeTypeFontGenerator(Gdx.files.internal("resources/OpenSans-Regular.ttf"));
		createFont(openSans, 25);
		openSans.dispose();

		createQuestions();
		createStage();
		createLabel();
		createButton();
		created = true;
		
		game.problemScreen = this;
	}

	private void createQuestions() {
		math = new mathQCreator();
		math.runOperation(6,level,1,1);
		answers = math.getAnswers();
		questions = math.getQuestions();

		for(int i = 0; i < answers[0].length; i++){
			System.out.println(answers[0][i]);
		}

	}

	private void createButton() {
		Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas();
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		buttons = new TextButton[5];
		text.setColor(Color.WHITE);


		for(int i = 0; i < 4; i++){
			buttonAtlas = new TextureAtlas(Gdx.files.internal("Menus/buton.pack"));
			skin.addRegions(buttonAtlas);
			textButtonStyle = new TextButtonStyle();
			textButtonStyle.font = text;
			textButtonStyle.up = skin.getDrawable("MenuItem");
			buttons[i] = new TextButton(""+answers[0][i], textButtonStyle);
			buttons[i].pad(20);

			final int num = i;

			buttons[i].addListener(new InputListener() {
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

					if(buttons[num].getLabel().textEquals(""+math.getCorrectAnswer(0))){

						/*
						 * For now will just make it go back to the menuScreen
						 */
						if(gamemode == 1){
							Gdx.input.setInputProcessor(previousProcessor);
//							game.setScreen(game.previousScreen);
//							game.manageScreens(game.previousScreen);
							game.setScreen(game.gameScreen);
							game.correctAns();
							dispose();
						}
						else if(level == 10){
							game.setScreen(new ProblemScreen(game, camera, sb, 0, level));
							dispose();
						}
						else {
							game.setScreen(new ProblemScreen(game, camera, sb, 0, level+1));
							dispose();
						}
					}
					else {
						if(gamemode == 1){
							
							Gdx.input.setInputProcessor(previousProcessor);
//							game.setScreen(game.previousScreen);
//							game.manageScreens(game.previousScreen);
							game.setScreen(game.gameScreen);
							dispose();
						}
						else{
							game.incorrectAns();
							if(game.getIncorrect()==3){
								game.resetAns();
								game.setScreen(new MenuScreen(game, camera, sb));
								dispose();
							}
						}

					}
					return true;
				}

				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					// Do nothing?
				}
			});

			table.row();
			table.add(buttons[i]);
		}

		stage.addActor(table);
	}

	private void createLabel() {
		LabelStyle ls = new LabelStyle();
		ls.font = text;
		ls.fontColor = Color.WHITE;
		label = new Label("What is the answer to the following question: "
				+questions[0],ls);
		table.add(label);
		stage.addActor(table);
	}

	private void createStage(){
		stage = new Stage();
		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.input.setInputProcessor(stage);
	}
	
	public void playStage(){
		if(Gdx.input.getInputProcessor() != stage){
			Gdx.input.setInputProcessor(stage);
		}
		stage.draw();
	}

	private void createFont(FreeTypeFontGenerator ftfg, float dp){
		text = ftfg.generateFont((int)(dp * Gdx.graphics.getDensity()));
		text.setColor(Color.RED);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		game.clear();

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
