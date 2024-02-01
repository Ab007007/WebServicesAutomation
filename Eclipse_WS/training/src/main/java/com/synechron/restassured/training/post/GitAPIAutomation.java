package com.synechron.restassured.training.post;

import java.util.Date;

import org.testng.annotations.Test;

import com.synechron.restassured.training.globals.GlobalVariables;


import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class GitAPIAutomation {

	
	public String project_Name = null;
	@Test(priority = 1)
	public void createRepository()
	{
		baseURI = GlobalVariables.git_baseURL;
		
		String dateandtime = new Date().toString().replace(" ", "_").replace(":", "_");
		project_Name = "API_Demo_" + dateandtime;
		String payloadAsString = "{\r\n" + 
				"    \"name\": \""+project_Name+"\",\r\n" + 
				"    \"description\": \"This is your first repo!\",\r\n" + 
				"    \"homepage\": \"https://github.com/Ab007007\",\r\n" + 
				"    \"private\": false,\r\n" + 
				"    \"is_template\": true\r\n" + 
				"}";
		
		given().
			headers("Authorization", GlobalVariables.git_token).
			headers("Content-Type","application/json").
			body(payloadAsString).log().body().
		when().
			post("/user/repos").
		then().
			assertThat().statusCode(201).
			body("name", equalTo(project_Name)).log().body();	
		
	}
	

	@Test(priority = 2)
	public void getRepositories() 
	{
		baseURI = GlobalVariables.git_baseURL;
		basePath ="/users/"+ GlobalVariables.git_User +"/repos";

		given().
			headers("Authorization",GlobalVariables.git_token).
			headers("Accept","*/*").
			queryParam("type", "owner").
			queryParam("sort", "created").
		when().
		get().
			then().
		assertThat().statusCode(200).log().body();
		
	}
	
	@Test(priority = 3)
	public void getRNewlyCreatedRepository() 
	{
		baseURI = GlobalVariables.git_baseURL;
		basePath ="/repos/"+ GlobalVariables.git_User +"/"+ project_Name;


		given().
			headers("Authorization",GlobalVariables.git_token).
			headers("Accept","*/*").
			queryParam("type", "owner").
			queryParam("sort", "created").
		when().
		get().
			then().
		assertThat().statusCode(200).log().body();
		
	}
	
	
	@Test(priority = 4)
	public void deleteRepository() 
	{
		baseURI = GlobalVariables.git_baseURL;
		basePath ="/repos/"+ GlobalVariables.git_User +"/"+ project_Name;


		given().
			headers("Authorization",GlobalVariables.git_token).
			headers("Accept","*/*").
		when().
		delete().
			then().
		assertThat().statusCode(204);
	}
}
