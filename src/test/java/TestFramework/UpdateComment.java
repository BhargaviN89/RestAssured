package TestFramework;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import TestFramework.ReusableMethods;
import TestFramework.payload;

public class UpdateComment {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getResources() throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
	
	@Test
	public void updateJiraComment()
	{
			RestAssured.baseURI = prop.getProperty("JiraHost");
			
			Response res = given().header("Content-Type", "application/json").
			header("Cookie", "JSESSIONID="+ReusableMethods.createJiraSession()).
			body(payload.updateJiraCommentPayload()).
			when().put("/rest/api/2/issue/10200/comment/10100").
			then().assertThat().statusCode(200).
			extract().response();
			
			JsonPath j = ReusableMethods.rawToJson(res);
			String commentId = j.get("id");
			System.out.println(commentId);
			
		
		
	}
}

