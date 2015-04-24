package game;

public class Score {

	String user;
	int score;
	
	public Score(String user, int score){
		this.user = user;
		this.score = score;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}