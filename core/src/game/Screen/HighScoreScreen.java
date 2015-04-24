package game.Screen;

import game.TTmath;
import game.Camera.OrthoCamera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Json;

public class HighScoreScreen implements Screen{
	private Table table;
//	private Label label;
	private BitmapFont text;
	private Stage stage;
	private TTmath game;
	private OrthoCamera camera;
	private SpriteBatch sb;
	
	public HighScoreScreen(TTmath game, OrthoCamera camera, SpriteBatch sb){
		this.game = game;
		this.camera = camera;
		this.sb = sb;

		FreeTypeFontGenerator TEXT_8BIT = new FreeTypeFontGenerator(Gdx.files.internal("resources/Minecraftia-Regular.ttf"));
		createFont(TEXT_8BIT, 25);
		TEXT_8BIT.dispose();
		
		createStage();
		createLabel("High Scores!", true);
		for(int i = 0; i < 3; i++){
			table.row();
			createLabel("Player "+i+" - ",false);
			createLabel(""+(i+100),false);
		}
		
		sendRequest();

	}
	
	private void createFont(FreeTypeFontGenerator ftfg, float dp){
    	
        text = ftfg.generateFont((int)(dp * Gdx.graphics.getDensity()));
        text.setColor(Color.WHITE);
        
    }
	
	public void sendRequest() {
		 
    	final Json json = new Json();
    	
        String requestJson = json.toJson(score); // this is just an example
 
        Net.HttpRequest request = new Net.HttpRequest(method);
        final String url = "http://107.181.168.88/retrieve.php";
        request.setUrl(url);
 
        request.setContent(requestJson);
 
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");
 
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
        	
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
 
                int statusCode = httpResponse.getStatus().getStatusCode();
                if(statusCode != HttpStatus.SC_OK) {
                    System.out.println("Request Failed");
                    return;
                }
 
                String responseJson = httpResponse.getResultAsString();
                try {
                	
 
                	//DO some stuff with the response string
                
                }
                catch(Exception exception) {
                   
                	exception.printStackTrace();
                }
            }
 
            public void failed(Throwable t) {
            	 System.out.println("Request Failed Completely");
            }

			@Override
			public void cancelled() {
				// TODO Auto-generated method stub
				
			}
 
//			@Override
//			public void cancelled() {
//				System.out.println("request cancelled");
//				
//			}
 
        });
 
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
