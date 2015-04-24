package game.Screen;

import game.JsonScore;
import game.Score;
import game.TTmath;
import game.Camera.OrthoCamera;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

public class HighScoreScreen implements Screen{
	private Table table;
//	private Label label;
	private BitmapFont text;
	private Stage stage;
	private TTmath game;
	private int gameScore;
	private OrthoCamera camera;
	private SpriteBatch sb;
	private JsonScore jScore;
	private TextButton mainMenu;
	private TextButton submit;
	
	public HighScoreScreen(TTmath game, OrthoCamera camera, SpriteBatch sb){
		this.game = game;
		this.camera = camera;
		this.sb = sb;
		gameScore = game.getScore();
		jScore = new JsonScore();
		ArrayList<Score> score = jScore.getHighScoreList();

		FreeTypeFontGenerator TEXT_8BIT = new FreeTypeFontGenerator(Gdx.files.internal("resources/Minecraftia-Regular.ttf"));
		createFont(TEXT_8BIT, 25);
		TEXT_8BIT.dispose();
		
		createStage();
		createLabel("High Scores!", true);
		for(int i = 0; i < score.size(); i++){
			table.row();
			createLabel(score.get(i).getUser(),false);
			createLabel(Integer.toString(score.get(i).getScore()),false);
		}
		createButon();
	}
	
	private void createFont(FreeTypeFontGenerator ftfg, float dp){
    	
        text = ftfg.generateFont((int)(dp * Gdx.graphics.getDensity()));
        text.setColor(Color.WHITE);
        
    }
	
	private void createStage(){
        stage = new Stage();
        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(stage);
    }
	
	private void createLabel(String labelText, boolean span){
		LabelStyle ls = new LabelStyle();
		ls.font = text;
		ls.fontColor = Color.WHITE;
		Label label = new Label(labelText,ls);
		
		if(span){
			table.add(label).height(Gdx.graphics.getDensity()*30).colspan(2);
		}
		else{
			table.add(label).height(Gdx.graphics.getDensity()*30);
//			.width(Gdx.graphics.getDensity()*40);
		}
		stage.addActor(table);
	}
	
	private void createButon(){
        Skin skin = new Skin();
        TextureAtlas buttonAtlas;
        TextButtonStyle textButtonStyle;

        buttonAtlas = new TextureAtlas(Gdx.files.internal("Menus/buton.pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = text;
        textButtonStyle.up = skin.getDrawable("MenuItem");
        
        TextFieldStyle tfs = new TextFieldStyle();
        tfs.font = text;
        tfs.fontColor = Color.WHITE;
        TextField nameText = new TextField("NAME",tfs);

        
        //  Submit button
        submit = new TextButton("Submit Score", textButtonStyle);

        submit.padTop(40 * Gdx.graphics.getDensity());
        submit.padBottom(15 * Gdx.graphics.getDensity());
        submit.padLeft(15 * Gdx.graphics.getDensity());
        submit.padRight(15 * Gdx.graphics.getDensity());

        submit.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            	// Do something here!
            	jScore.sendHighScore(new Score("na", 2000));
            	game.setScreen(new MenuScreen(game, camera, sb));
            	dispose();
            }
        });
        

        //  Main menu button
        mainMenu = new TextButton("Back to Home", textButtonStyle);

        mainMenu.padTop(40 * Gdx.graphics.getDensity());
        mainMenu.padBottom(15 * Gdx.graphics.getDensity());
        mainMenu.padLeft(15 * Gdx.graphics.getDensity());
        mainMenu.padRight(15 * Gdx.graphics.getDensity());

        mainMenu.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game, camera, sb));
                dispose();
            }
        });
        

        table.row();
        table.add(submit);
        table.add(nameText).width(Gdx.graphics.getDensity()*150)
        .height(Gdx.graphics.getDensity()*40);
        table.row();
        table.add(mainMenu);
        stage.addActor(table);
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
        game.clear();

        camera.update();

        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.resetScore();

            game.setScreen(new MenuScreen(game, camera, sb));
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
		
	}

}
