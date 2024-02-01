package com.synechron.restassured.training.logs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class LoggingDemo {

	
	
	@Test
	public void logRequestAndResponse() {
		System.out.println("API Call Started...");

		RestAssured.baseURI = "https://api.trello.com";
//		RestAssured.port = "8888";
		RestAssured.basePath = "1/boards/65b8926befbc0ef1ab5da901";

		 given().log().all().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
		when().
			get().
		then().log().all()
			.assertThat().statusCode(200)
			.body("name", equalTo("PostMan-APIBoard"))
			.contentType(ContentType.JSON);
		System.out.println("API Call Ended...");
	}
	
	
	
	
	@Test
	public void logRequestAndResponseOnly() {
		System.out.println("API Call Started...");

		RestAssured.baseURI = "https://api.trello.com";
//		RestAssured.port = "8888";
		RestAssured.basePath = "1/boards/65b8926befbc0ef1ab5da901";

		 given().log().params().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
		when().
			get().
		then().log().headers()
			.assertThat().statusCode(200)
			.body("name", equalTo("PostMan-APIBoard"))
			.contentType(ContentType.JSON);
		System.out.println("API Call Ended...");
	}
	
}
