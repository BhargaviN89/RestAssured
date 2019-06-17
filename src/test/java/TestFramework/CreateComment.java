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


public class CreateComment {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getResources() throws IOException
	{
		System.out.println(System.getProperty("user.dir"));
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
	
	@Test
	public void createJiraComment()
	{
		RestAssured.baseURI = prop.getProperty("JiraHost");
		
		Response res = given().header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID="+ReusableMethods.createJiraSession()).
		body(payload.createJiraCommentPayload()).
		when().post("/rest/api/2/issue/10200/comment").
		then().assertThat().statusCode(201).
		extract().response();
		
		JsonPath j = ReusableMethods.rawToJson(res);
		String commentId = j.get("id");
		System.out.println(commentId);
		
	}

}
