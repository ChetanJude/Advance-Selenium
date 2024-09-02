package practice;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Working_with_json_ReadDataFromJsonFiletest {

	public static void main(String[] args) throws Throwable, IOException, ParseException {
		JSONParser parser= new JSONParser();
		Object obj = parser.parse(new FileReader("./TestData/appcommondata.json"));
		JSONObject map= (JSONObject) obj;
		System.out.println(map.get("url"));
		System.out.println(map.get("browser"));
		System.out.println(map.get("username"));
		System.out.println(map.get("password"));
		System.out.println(map.get("timeout"));
	}

}
