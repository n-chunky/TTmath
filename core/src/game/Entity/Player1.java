package game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player1 extends Sprite {

	//movement velocity
	private Vector2 velocity = new Vector2();
	private float speed = 60 * 2, gravity = 60 * 1.8f;
	
	public Player1(Sprite sprite){
		super(sprite);
	}
	
	@Override
	public void draw(Batch batch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta){
//		//apply gravity
//		velocity.y -=gravity * delta;
//		
//		// clamp velocity
//		if(velocity.y > speed)
//			velocity.y = speed;
//		else if(velocity.y < -speed)
//			velocity.y = -speed;
		
		setX(getX() + velocity.x * delta);
		setY(getY() + velocity.y * delta);
	}
}