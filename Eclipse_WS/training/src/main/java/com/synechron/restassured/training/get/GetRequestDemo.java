package com.synechron.restassured.training.get;

import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;

public class GetRequestDemo 
{
	public static void main(String[] args)
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
}
