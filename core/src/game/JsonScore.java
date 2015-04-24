package game;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class JsonScore {

	JsonArray array;
	ArrayList<Score> scores;
	
	public JsonScore(){	}

	public ArrayList<Score> getHighScoreList(){
		try {
			array = sendGet();
			scores = JsonArrayToScoreList(array);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(scores != null)	return scores;
		else return new ArrayList<Score>();
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
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		
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
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		
		OutputStreamWriter out= new OutputStreamWriter(con.getOutputStream());
		out.write(object.toString());
		System.out.println(object.toString());
		out.flush();
		out.close();

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
	
	
	public JsonArray getJsonArray() {
		return array;
	}

}

