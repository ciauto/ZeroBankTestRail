package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class Programe {

	
	public static void main(String[] args) throws MalformedURLException, IOException, APIException {
		// TODO Auto-generated method stub
		
		APIClient client = new APIClient("https://yeswecan.testrail.io/");
		client.setUser("test rail email id");
		client.setPassword("test rail password");
		JSONObject c = (JSONObject) client.sendGet("get_case/2");
		System.out.println(c.get("title"));
		
		Map data = new HashMap();
		data.put("status_id", new Integer(5));
		data.put("comment", "This test is Failed");
		JSONObject r = (JSONObject) client.sendPost("add_result_for_case/1/2", data);

	}

}
