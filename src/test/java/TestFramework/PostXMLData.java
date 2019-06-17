package TestFramework;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import TestFramework.ReusableMethods;
public class PostXMLData {
	
	@Test
	public void postXMLData() throws IOException
	{
		String xmlPayload = GenerateStringFromResource("C:\\Users\\Lenovo\\Downloads\\work\\Rest Assured\\xmlpostData.xml");
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response xml = given().
		queryParam("key","qaclick123").
		body(xmlPayload).
		
		when().
		post("/maps/api/place/add/xml").
		
		then().
		assertThat().statusCode(200).and().contentType(ContentType.XML).
		
		extract().response();
		
		XmlPath x = ReusableMethods.rawToXML(xml);
		String placeid = x.get("response.place_id");
		System.out.println(placeid);
	}

	public static String GenerateStringFromResource(String string) throws IOException {
		// TODO Auto-generated method stub
		return new String(Files.readAllBytes(Paths.get(string)));
	}
	
	

}
