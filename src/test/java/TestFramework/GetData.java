package TestFramework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class GetData {
	
	@Test
	public void getData() {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
		param("location","-33.8670522,151.1957362").
		param("radius","1500").
		param("key","AIzaSyBryBYbeOtu2Umfa_qi8WaWoHvben6exfk").
		
		when().
		get("/maps/api/place/nearbysearch/json").
		
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK"));

	}

}
