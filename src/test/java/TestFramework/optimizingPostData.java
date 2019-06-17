package TestFramework;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import  TestFramework.resourcesData;
import  TestFramework.payload;

public class optimizingPostData {
	
	private static Logger log = LogManager.getLogger(optimizingPostData.class.getName());
	
	Properties prop = new Properties();
	@BeforeTest
	public void getResources() throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
	
	@Test
	public void postData()
	{
		log.info("Host Information : "+prop.getProperty("Host"));
		RestAssured.baseURI = prop.getProperty("Host");
		
		Response res = given().
		queryParam("key", prop.getProperty("key")).
		body(payload.payloadData()).
		
		when().
		post(resourcesData.placeResourcesData()).
		
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK")).
		
		extract().response();
		
		String response = res.asString();
		log.info(response);	
		JsonPath j = new JsonPath(response);
		String placeid = j.get("place_id");
		log.info(placeid);
		
	}
	
	

}
