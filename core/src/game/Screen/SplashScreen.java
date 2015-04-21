package game.Screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
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

    public SplashScreen(TTmath game, OrthoCamera camera, SpriteBatch sb){
        this.game = game;
        this.camera = camera;
        this.sb = sb;

//      game.manageScreens(this);
    }

    @Override
    public void show() {
        texture = TextureManager.SPLASH;
        startTime = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {
        sb.begin();
        sb.draw(texture, 0, 0);
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
