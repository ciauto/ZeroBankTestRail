package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class ProgrameQ4 {

	
	public static void main(String[] args) throws MalformedURLException, IOException, APIException {
		// TODO Auto-generated method stub
		
		APIClient client = new APIClient("https://q4web.testrail.com/");
		client.setUser("test rail email id");
		client.setPassword("test rail password");
		JSONObject c = (JSONObject) client.sendGet("get_case/288046");
		System.out.println(c.get("title"));
		
		

	}

}
