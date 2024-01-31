package com.synechron.restassured.training.get;

import org.testng.annotations.Test;

import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithDifferentFormat 
{
	@Test
	public void verifyBoardInBDDFormat()
	{
		System.out.println("API Call Started...");
		RestAssured.
			given().
				param("key", GlobalVariables.key).
				param("token",GlobalVariables.token).
			when().
				get("https://api.trello.com/1/boards/65b8926befbc0ef1ab5da901").
			then().
				assertThat().statusCode(200);
		
		System.out.println("API Call Ended...");
		
	}
	
	
	@Test
	public void verifyBoardInGivenExpectFormat()
	{
		System.out.println("API Call Started...");
		RestAssured.
			given().
				param("key", GlobalVariables.key).
				param("token",GlobalVariables.token).
			expect().
				statusCode(200).
			when().
				get("https://api.trello.com/1/boards/65b8926befbc0ef1ab5da901");
		System.out.println("API Call Ended...");
	}
	
	
	@Test
	public void verifyBoardUsingRestAssuredFormat()
	{
		System.out.println("Test Started");
		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.param("key", GlobalVariables.key);
		reqSpec.param("token", GlobalVariables.token);
		reqSpec.header("accept", "application/json");
		
		Response response = reqSpec.get("https://api.trello.com/1/boards/65b8926befbc0ef1ab5da901");
		
		response.prettyPrint();
		
		ValidatableResponse responseValidate = response.then();
		
		responseValidate.statusCode(200);
		System.out.println("Test Ended");
	}
	
	
	
}
