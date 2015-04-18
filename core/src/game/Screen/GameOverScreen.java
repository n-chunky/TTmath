package game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import game.Camera.OrthoCamera;
import game.TTmath;

public class GameOverScreen implements Screen{
    private BitmapFont text;
    private OrthoCamera camera;
    private TTmath game;
    private Label gameOverLabel;
    private Table table;
    private Stage stage;
    private TextButton gameOverButton;
    SpriteBatch sb;

    public GameOverScreen(TTmath game, OrthoCamera camera, SpriteBatch sb) {
        this.game = game;
        this.camera = camera;
        this.sb = sb;

        game.manageScreens(this);

        FreeTypeFontGenerator openSans = new FreeTypeFontGenerator(Gdx.files.internal("resources/OpenSans-Regular.ttf"));
        createFont(openSans, 25);
        openSans.dispose();

        createStage();
        createLabel();
        createButton();
    }

    private void createStage(){
        stage = new Stage();
        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(stage);
    }

    private void createLabel() {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = text;
        ls.fontColor = Color.RED;
        gameOverLabel = new Label("GAME OVER", ls);

        table.add(gameOverLabel);
        table.row();
        stage.addActor(table);
    }

    private void createFont(FreeTypeFontGenerator ftfg, float dp){
        text = ftfg.generateFont((int)(dp * Gdx.graphics.getDensity()));
        text.setColor(Color.RED);
    }

    private void createButton(){
        Skin skin = new Skin();
        TextureAtlas buttonAtlas;
        TextButtonStyle textButtonStyle;

        buttonAtlas = new TextureAtlas(Gdx.files.internal("Menus/buton.pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = text;
        textButtonStyle.up = skin.getDrawable("MenuItem");
        gameOverButton = new TextButton("Back to Home", textButtonStyle);

        gameOverButton.pad(20);

        gameOverButton.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


                game.setScreen(new MenuScreen(game, camera, sb));

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {


            }
        });


        table.add(gameOverButton);
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
    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void hide() {

    }
}
