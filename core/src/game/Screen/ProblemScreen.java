package game.Screen;

import game.TTmath;
import game.Camera.OrthoCamera;
import game.MathAlgorithms.mathQCreator;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ProblemScreen implements Screen{
	private BitmapFont text;
	private OrthoCamera camera;
	private TTmath game;
	private int[][] answers;
	private String[] questions;
	SpriteBatch sb;

	public ProblemScreen(TTmath game, OrthoCamera camera, SpriteBatch sb){
		this.camera = camera;
		this.game = game;
		this.sb = sb;

		text = new BitmapFont();
		text.setColor(Color.RED);
//		text.setScale(1);
//		text.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);

//		mathQCreator QA = new mathQCreator(1, 1, 1, 1);
//		answers = QA.getAnswers();
//		questions = QA.getQuestions();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		game.clear();
		
		camera.update();
		
		sb.begin();
		for(int i = 0; i < questions.length; i++){
			text.draw(sb, questions[i], camera.position.x, camera.position.y + i*10);
		}
        sb.end();
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
