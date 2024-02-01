package com.synechron.restassured.training.specifictions;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TrelloAPIAutomationUsingSpec {
	
	ValidatableResponse vResponse = null;
	public String boardID = null;
	public String listID = null;
	public String cardID = null;
	
	public RequestSpecification reqSpec = null;
	public RequestSpecBuilder reqBuilder = null;
	
	public ResponseSpecification resSpec = null;
	public ResponseSpecBuilder resBuilder = null;
	
	@BeforeMethod
	public void initializeConstants()
	{
		RestAssured.baseURI = "https://api.trello.com";
		
		reqBuilder = new RequestSpecBuilder();
		reqBuilder.addQueryParam("key", GlobalVariables.key);
		reqBuilder.addQueryParam("token", GlobalVariables.token);
		reqBuilder.addHeader("Content-type", "application/json");
		reqBuilder.log(LogDetail.ALL);
		
		reqSpec = reqBuilder.build();
		
		resBuilder = new ResponseSpecBuilder();
		resBuilder.expectStatusCode(200);
//		resBuilder.expectHeader("Server", "AtlassianEdge");
		resBuilder.log(LogDetail.ALL);
		
		resSpec = resBuilder.build();
		
		
	}
	
	@AfterMethod
	public void resetConstants()
	{
		RestAssured.reset();
		
	}
	
	@Test(priority = 1)
	public void createBoard() 
	{
		//RestAssured.basePath = "/1/boards/";
		
		vResponse = given().
				spec(reqSpec).
				queryParam("name", "Eclipse-Created-Board").
		when().
			post("/1/boards/").
		then().spec(resSpec);

		boardID = vResponse.extract().path("id");
				//65b9eedf65115121b64a0fd6

	}
	
	@Test(priority = 3)
	public void createList()
	{
		//RestAssured.basePath = "/1/lists/";
		
		ValidatableResponse response = given().
				spec(reqSpec).
				queryParam("name", "Eclipse-Created-List").
				queryParam("idBoard", boardID).
			when().
				post("/1/lists/")
			.then().spec(resSpec);

		listID = response.extract().path("id");
				
				
	}
	
	@Test(priority = 4)
	public void createMultipleCard()
	{
		for (int i = 0; i < 10; i++) {
			String dateandtime = new Date().toString().replace(" ", "_").replace(":", "_");
			
			ValidatableResponse response = given().
					spec(reqSpec).
					queryParam("name", "Eclipse-Created-Card-" + dateandtime).
					queryParam("idList", listID).
					header("Content-type", "appliction/json").
				when().
					post("/1/cards/").
				then().spec(resSpec);
			
		}
				//65b9f4f6bea53f6907d3d985
	}
	
	@Test(priority = 2)
	public void verifyBoardInGivenExpectFormat()
	{
		System.out.println("API Call Started...");
		
			given().
				spec(reqSpec).
			when().
				get("1/boards/" + boardID).
			then().
				spec(resSpec);
			;
		System.out.println("API Call Ended...");
	}
	
}
