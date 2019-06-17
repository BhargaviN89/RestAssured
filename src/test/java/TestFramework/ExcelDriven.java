package TestFramework;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import TestFramework.ReusableMethods;
import TestFramework.payload;
import TestFramework.resourcesData;

public class ExcelDriven {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getResources() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\eclipse-workspace\\RestAssured\\src\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void excelDriven() throws IOException
	{
		RestAssured.baseURI = prop.getProperty("LibHost");
		
		Response res = given().
		body(payload.addBookExcelPayload()).
		
		when().
		post(resourcesData.libResourcesData()).
		
		then().
		assertThat().statusCode(200).
		
		extract().response();
		
		JsonPath j = ReusableMethods.rawToJson(res);
		String id = j.get("ID");
		String message = j.getString("Msg");
		
		System.out.println(id);
		System.out.println(message);
		
		
	}
	
	

}
