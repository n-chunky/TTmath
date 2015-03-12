package game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {

	//motion holds forward, backwards, left, and right animation for Player
	//forward is 1, backwards 2, left 3, right 4
	private TextureRegion[][] motion;
	private float time;
	private float delay;
	private int currentFrame;
	private int[] timesPlayed = new int[4];

	public Animation() {}

	public Animation(TextureRegion[][] frames) {
		this(frames, 1 / 12f);
	}

	public Animation(TextureRegion[][] frames, float delay) {
		setFrames(frames, delay);
	}

	public void setFrames(TextureRegion[][] frames, float delay) {
		motion = frames;
		this.delay = delay;
		time = 0;
		currentFrame = 0;
		for(int i=0;i<timesPlayed.length;i++){
			timesPlayed[i] = 0;
		}
	}

	//int animation refers to the specific animation in the 2d motion array
	public void update(TextureRegion[] frames, int animation, float dt) {
		if(delay <= 0) return;
		time += dt;
		while(time >= delay) {
			step(frames, animation);
		}
	}

	private void step(TextureRegion[] frames, int animation) {
		time -= delay;
		currentFrame++;
		if(currentFrame == frames.length) {
			currentFrame = 0;
			timesPlayed[animation]++;
		}
	}

//	public TextureRegion getFrame() { return frames[currentFrame]; }
//	public int getTimesPlayed(int animation) { return timesPlayed; }

}













