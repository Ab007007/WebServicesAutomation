package com.synechron.restassured.training.post;

import static io.restassured.RestAssured.given;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class CreateList {

	
	@BeforeMethod
	public void initializeConstants()
	{
		RestAssured.baseURI = "https://api.trello.com";
		RestAssured.basePath = "/1/lists/";
		
	}
	
	@AfterMethod
	public void resetConstants()
	{
		RestAssured.reset();
		
	}
	
	@Test
	public void createList()
	{
		Response response = given().
				queryParam("key", GlobalVariables.key).
				queryParam("token",GlobalVariables.token).
				queryParam("name", "Eclipse-Created-List").
				queryParam("idBoard", "65b9eedf65115121b64a0fd6").
				header("Content-type", "appliction/json").
			when().
				post();
		
				response.prettyPrint();
				
				ValidatableResponse vResponse = response.then();
				vResponse.statusCode(200);
				
				//65b9f41581135c9d6b66026b
	}
	
	
	
	
}
