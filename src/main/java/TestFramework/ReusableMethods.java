package TestFramework;

import java.io.FileInputStream;
import static io.restassured.RestAssured.given;




import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	
	public static JsonPath rawToJson(Response s) {
		
		String response = s.asString();
		JsonPath j = new JsonPath(response);
		return j;
		
	}
	
	public static XmlPath rawToXML(Response s) {
		
		String response = s.asString();
		XmlPath x = new XmlPath(response);
		return x;
		
	}
	
	
	public static String createJiraSession()
	{
		RestAssured.baseURI = "http://localhost:8080";
		
		Response res = given().header("Content-Type", "application/json").
		body(payload.jiraSessionPayload()).
		when().post(resourcesData.jiraSessionResoucesData()).
		then().assertThat().statusCode(200).
		extract().response();
		
		JsonPath j = ReusableMethods.rawToJson(res);
		String sessionId = j.get("session.value");
		System.out.println(sessionId);
		return sessionId;
		
	}

}
