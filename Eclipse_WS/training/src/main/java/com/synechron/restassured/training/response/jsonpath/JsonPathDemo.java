package com.synechron.restassured.training.response.jsonpath;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathDemo {

	Response response = null;
	JsonPath responseBody = null;
	@BeforeTest
	public void verifyBoardInBDDFormat() {
		System.out.println("API Call Started...");

		RestAssured.baseURI = "https://api.trello.com";
//		RestAssured.port = "8888";
		RestAssured.basePath = "1/boards/65b8926befbc0ef1ab5da901";

		response = given().param("key", GlobalVariables.key).param("token", GlobalVariables.token).when().get();
		responseBody = new JsonPath(response.asString());
		
		System.out.println("API Call Ended...");
	}
	
	@Test
	public void printID()
	{
		JsonPath responseBody = new JsonPath(response.asString());
		String id = responseBody.get("id");		
		System.out.println("Printing ID : " + id);
	}
	

	@Test
	public void printAllUrls() {
		System.out.println("---------------printAllUrls Started ----------------");
		int totalUrls = responseBody.get("prefs.backgroundImageScaled.size()");
		System.out.println(totalUrls);
		for (int i = 0; i < totalUrls; i++) 
		{
			String url = responseBody.get("prefs.backgroundImageScaled["+i+"].url");
			System.out.println(url);
		}
		System.out.println("---------------printAllUrls Ended ----------------");
	}
}
