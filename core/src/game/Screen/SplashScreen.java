package game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import game.Camera.OrthoCamera;
import game.TTmath;
import game.TextureManager;

public class SplashScreen implements Screen{

    private TTmath game;
    private OrthoCamera camera;
    private SpriteBatch sb;
    private long startTime;
    private Texture texture;
    private Sprite sprite;
    private final int HEIGHT, WIDTH;

    public SplashScreen(TTmath game, OrthoCamera camera, SpriteBatch sb){
        this.game = game;
        this.camera = camera;
        this.sb = sb;

        HEIGHT = Gdx.graphics.getHeight();
        WIDTH  = Gdx.graphics.getWidth();

//      game.manageScreens(this);
    }

    @Override
    public void show() {
        texture = TextureManager.SPLASH;
        startTime = TimeUtils.millis();

//        TextureRegion region = new TextureRegion(texture, 0, 0,
//                WIDTH, HEIGHT);
        sprite = new Sprite(texture);

        sprite.setSize(WIDTH, HEIGHT);

    }

    @Override
    public void render(float delta) {
//        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sprite.draw(sb);
        sb.end();

        if(TimeUtils.millis() > (startTime+1000)){
            game.setScreen(new MenuScreen(game, camera, sb));
        }

    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void dispose() {
        texture.dispose();
        sb.dispose();
    }
}
