package TestFramework;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import TestFramework.ReusableMethods;
import TestFramework.payload;
import TestFramework.resourcesData;

public class DynamicJson {
	
	Properties prop = new Properties();
	@BeforeTest
	public void getResources() throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//env.properties");
		prop.load(fis);
	}
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI = prop.getProperty("LibHost");
		
		Response res = given().
		body(payload.addBookPayload(isbn, aisle)).
		
		when().
		post(resourcesData.libResourcesData()).
		
		then().
		assertThat().statusCode(200).
		
		extract().response();
		
		JsonPath j = ReusableMethods.rawToJson(res);
		String id = j.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"fsafdes","453452"},
						{"dgdddf","545662"},
						{"dhgsghd","6537662"}};
	}

}
