package com.synechron.restassured.training.post;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class CreateCardUsingFaker {

	
	@BeforeMethod
	public void initializeConstants()
	{
		RestAssured.baseURI = "https://api.trello.com";
		RestAssured.basePath = "/1/cards/";
	}
	
	@AfterMethod
	public void resetConstants()
	{
		RestAssured.reset();
		
	}
	
	@Test
	public void createCard()
	{
		Faker faker = new Faker();
		String name = faker.company().name();
		Response response = given().
				queryParam("key", GlobalVariables.key).
				queryParam("token",GlobalVariables.token).
				queryParam("name", "Meeting with -" + name).
				queryParam("idList", "65b9f41581135c9d6b66026b").
				header("Content-type", "appliction/json").
			when().
				post();
		
				response.prettyPrint();
				
				ValidatableResponse vResponse = response.then();
				vResponse.statusCode(200);
				
				//65b9f4f6bea53f6907d3d985
	}
	
	
	
	
	@Test
	public void getAllCards()
	{
		
		
		
	}
	
	
	@Test
	public void deleteCards() {
		
	}
}
