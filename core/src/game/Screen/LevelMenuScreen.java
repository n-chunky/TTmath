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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import game.Camera.OrthoCamera;
import game.TTmath;

public class LevelMenuScreen implements Screen{
    private BitmapFont text;
    private OrthoCamera camera;
    private TTmath game;
    private Label label;
    private Stage stage;
    private Table table;
    private Skin skin;
    private TextButton buttons[];
    SpriteBatch sb;

    public LevelMenuScreen(TTmath game, OrthoCamera camera, SpriteBatch sb){
        this.camera = camera;
        this.game = game;
        this.sb = sb;

        game.manageScreens(this);

        FreeTypeFontGenerator openSans = new FreeTypeFontGenerator(Gdx.files.internal("resources/OpenSans-Regular.ttf"));
        createFont(openSans, 25);
        openSans.dispose();

        createStage();
        createLabel();
        createButton();
    }

    private void createButton() {
        Skin skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas();
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        buttons = new TextButton[5];
        text.setColor(Color.WHITE);

        for(int i = 0; i < 5; i++){
            buttonAtlas = new TextureAtlas(Gdx.files.internal("Menus/buton.pack"));
            skin.addRegions(buttonAtlas);
            textButtonStyle = new TextButtonStyle();
            textButtonStyle.font = text;
            textButtonStyle.up = skin.getDrawable("MenuItem");
            buttons[i] = new TextButton(""+Integer.toString(i+1), textButtonStyle);

            buttons[i].pad(80);


            final int levelSelect = i+1;

            buttons[i].addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                    game.setScreen(new GameScreen(game, camera, sb, levelSelect));

                    return true;
                }

                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    // Do nothing?
                }
            });

            table.add(buttons[i]);
        }

        stage.addActor(table);
    }

    private void createLabel() {
        LabelStyle ls = new LabelStyle();
        ls.font = text;
        ls.fontColor = Color.WHITE;
        label = new Label("Map Select", ls);
        table.add(label);
        stage.addActor(table);
    }

    private void createStage(){
        stage = new Stage();
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(stage);
    }

    private void createFont(FreeTypeFontGenerator ftfg, float dp){
        text = ftfg.generateFont((int)(dp * Gdx.graphics.getDensity()));
        text.setColor(Color.WHITE);
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
