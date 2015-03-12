package game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity{
	protected Texture texture;
	protected Sprite sprite;
	protected Vector2 pos, direction;
	protected TextureRegion textureReg;
	
	public Entity(Texture texture, Vector2 pos, Vector2 direction){
		this.texture = texture;
		this.pos = pos;
		this.direction = direction;
	}
	
	public abstract void update();
	
	public void setTextureRegion(int x, int y, int width, int height){
		textureReg = new TextureRegion(texture, x, y, width, height);	
	}
	
	public void setTextureRegion(TextureRegion region){
		textureReg = new TextureRegion(region);	
	}
	
	public void render(SpriteBatch sb){
		sb.draw(textureReg, pos.x, pos.y);
	}
	
	public Vector2 getPosition(){
		return pos;
	}
	
	public void setDirection(float x, float y){
		direction.set(x, y);
		direction.scl(Gdx.graphics.getDeltaTime());
	}
}
