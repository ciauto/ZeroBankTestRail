package testcases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class LoginTest {
	
WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://zero.webappsecurity.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	@Test
	public void verifyLogin() {
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.id("user_login")).sendKeys("username");
		driver.findElement(By.id("user_password")).sendKeys("password");
		driver.findElement(By.name("submit")).click();
		String actualText=driver.findElement(By.xpath("//a[contains(text(),'Account Summary')]")).getText();
		String expectedText = "AccountSummary";
		Assert.assertEquals(actualText, expectedText);
	}
		
	@AfterMethod
	public void tearDown(ITestResult result) throws MalformedURLException, IOException, APIException {
		if(result.getStatus()==ITestResult.FAILURE) {
			System.out.println("Failed Test: 	" +result.getMethod().getMethodName());
			APIClient client = new APIClient("https://yeswecan.testrail.io/");
			client.setUser("your test rail email ID");
			client.setPassword("test rail password");
			JSONObject c = (JSONObject) client.sendGet("get_case/1");
			System.out.println(c.get("title"));
			
			Map data = new HashMap();
			data.put("status_id", new Integer(5));
			data.put("comment", "This test is failed");
			JSONObject r = (JSONObject) client.sendPost("add_result_for_case/1/1", data);
		}
		if(result.getStatus()==ITestResult.SUCCESS){
			System.out.println("Passed Test: 	" +result.getMethod().getMethodName());
			APIClient client = new APIClient("https://yeswecan.testrail.io/");
			client.setUser("test rail email ID");
			client.setPassword("test rail password");
			JSONObject c = (JSONObject) client.sendGet("get_case/1");
			System.out.println(c.get("title"));
			
			Map data = new HashMap();
			data.put("status_id", new Integer(1));
			data.put("comment", "This test is passed");
			JSONObject r = (JSONObject) client.sendPost("add_result_for_case/1/1", data);
		}
	
		
		
		
		driver.quit();
	}
}
