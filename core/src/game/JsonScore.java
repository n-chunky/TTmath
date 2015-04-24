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

	JsonArray array;
	ArrayList<Score> scores;

	JsonScore(){
		try {
			array = sendGet();
			scores = JsonArrayToScoreList(array);
		} catch (Exception e) {
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
	
	public ArrayList<Score> JsonArrayToScoreList(JsonArray array){
		ArrayList<Score> scores = new ArrayList<Score>();
		
		for(int i=0;i<array.size();i++){
			JsonObject object = array.get(i).getAsJsonObject();
			scores.add(new Score(object.get("user").getAsString(), object.get("score").getAsInt()));
		}
		return scores;
	}
	
	public JsonArray getArray() {
		return array;
	}

	public ArrayList<Score> getScores() {
		return scores;
	}
	
}

