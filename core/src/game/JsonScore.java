package game;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class JsonScore {

	ArrayList<Score> scores;
	
	JsonScore(){	}
	
	public ArrayList<Score> getHighScoreList(){
		try {
			JsonArray array = sendGet();
			scores = JsonArrayToScoreList(array);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scores;
	}
	
	public void sendHighScore(Score score){
		JsonObject jObj = ScoreObjectToJsonObject(score);
		try {
			sendPost(jObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private JsonArray sendGet() throws Exception {

		String url = "http://107.181.168.88/retrieve.php";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "user");

		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JsonParser parser = new JsonParser();
		JsonArray array = (JsonArray)parser.parse(response.toString());

		return array;
	}
	
	private void sendPost(JsonObject object) throws Exception {

		String url = "http://107.181.168.88/send.php";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");

//		//add request header
//		con.setRequestProperty("User-Agent", "user");
//
//		int responseCode = con.getResponseCode();
//
//		BufferedReader in = new BufferedReader(
//				new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();

	}
	
	public ArrayList<Score> JsonArrayToScoreList(JsonArray array){
		ArrayList<Score> scores = new ArrayList<Score>();
		
		for(int i=0;i<array.size();i++){
			JsonObject object = array.get(i).getAsJsonObject();
			scores.add(new Score(object.get("user").getAsString(), object.get("score").getAsInt()));
		}
		return scores;
	}
	
	public JsonObject ScoreObjectToJsonObject(Score score){
		JsonObject obj = new JsonObject();
		JsonParser parser = new JsonParser();
		obj.add("user", parser.parse(score.getUser()));
		obj.add("score", parser.parse(Integer.toString(score.getScore())));
		return obj;
	}

}

