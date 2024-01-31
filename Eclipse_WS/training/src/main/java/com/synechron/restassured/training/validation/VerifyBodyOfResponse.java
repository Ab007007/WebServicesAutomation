package com.synechron.restassured.training.validation;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;

public class VerifyBodyOfResponse {

	
	
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
				assertThat().statusCode(200)
				.body("id", CoreMatchers.equalTo("65b8926befbc0ef1ab5da901"))
				.body("name", CoreMatchers.equalTo("PostMan-APIBoard"));
		
		System.out.println("API Call Ended...");
		
	}
	
}
