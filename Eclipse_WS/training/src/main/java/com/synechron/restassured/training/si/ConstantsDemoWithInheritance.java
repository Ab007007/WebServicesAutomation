package com.synechron.restassured.training.si;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import com.synechron.restassured.training.globals.ConfigurationClass;
import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;

public class ConstantsDemoWithInheritance extends ConfigurationClass
{
	
	@Test
	public void verifyBoardInBDDFormat()
	{
		System.out.println("API Call Started...");
		given().
			param("key", GlobalVariables.key).
			param("token",GlobalVariables.token).
		when().
			get().
		then().
			assertThat().statusCode(200)
			.body("id", equalTo("65b8926befbc0ef1ab5da901"))
			.body("name", equalTo("PostMan-APIBoard"));
		System.out.println("API Call Ended...");
	}
}
