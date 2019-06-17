package TestFramework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import TestFramework.ReusableMethods;
import TestFramework.payload;
import TestFramework.resourcesData;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class createIssue {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getResources() throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
	
	@Test
	public void createJiraIssue()
	{
		RestAssured.baseURI = prop.getProperty("JiraHost");
		
		Response res = given().header("Content-Type", "application/json").
		header("Cookie", "JSESSIONID="+ReusableMethods.createJiraSession()).
		body(payload.createJiraIssuePayload()).
		when().post(resourcesData.createJiraIssueResoucesData()).
		then().assertThat().statusCode(201).
		extract().response();
		
		JsonPath j = ReusableMethods.rawToJson(res);
		String issueId = j.get("id");
		System.out.println(issueId);
		
	}

}
