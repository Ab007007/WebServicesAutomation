package com.synechron.restassured.training.post;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.synechron.restassured.training.globals.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class TrelloAPIAutomation {
	
	ValidatableResponse vResponse = null;
	public String boardID = null;
	public String listID = null;
	public String cardID = null;
	
	
	@BeforeMethod
	public void initializeConstants()
	{
		RestAssured.baseURI = "https://api.trello.com";
		
	}
	
	@AfterMethod
	public void resetConstants()
	{
		RestAssured.reset();
		
	}
	
	@Test(priority = 1)
	public void createBoard() 
	{
		RestAssured.basePath = "/1/boards/";
		Response response = given().
				queryParam("key", GlobalVariables.key).
				queryParam("token",GlobalVariables.token).
				queryParam("name", "Eclipse-Created-Board").
				header("Content-type", "appliction/json").
			when().
				post();
				response.prettyPrint();
				vResponse = response.then();
				vResponse.statusCode(200);
				boardID = vResponse.extract().path("id");
				//65b9eedf65115121b64a0fd6

	}
	
	@Test(priority = 3)
	public void createList()
	{
		RestAssured.basePath = "/1/lists/";
		
		Response response = given().
				queryParam("key", GlobalVariables.key).
				queryParam("token",GlobalVariables.token).
				queryParam("name", "Eclipse-Created-List").
				queryParam("idBoard", boardID).
				header("Content-type", "appliction/json").
			when().
				post();
		
				response.prettyPrint();
				
				ValidatableResponse vResponse = response.then();
				vResponse.statusCode(200);
				
				listID = vResponse.extract().path("id");
				
				
	}
	
	@Test(priority = 4)
	public void createMultipleCard()
	{
		RestAssured.basePath = "/1/cards/";
		for (int i = 0; i < 10; i++) {
			String dateandtime = new Date().toString().replace(" ", "_").replace(":", "_");
			
			Response response = given().
					queryParam("key", GlobalVariables.key).
					queryParam("token",GlobalVariables.token).
					queryParam("name", "Eclipse-Created-Card-" + dateandtime).
					queryParam("idList", listID).
					header("Content-type", "appliction/json").
				when().
					post();
			
					response.prettyPrint();
					
					ValidatableResponse vResponse = response.then();
					vResponse.statusCode(200);
		}
		
				
				//65b9f4f6bea53f6907d3d985
	}
	
	@Test(priority = 2)
	public void verifyBoardInGivenExpectFormat()
	{
		RestAssured.basePath = "1/boards/";
		System.out.println("API Call Started...");
		RestAssured.
			given().
				param("key", GlobalVariables.key).
				param("token",GlobalVariables.token).
			expect().
				statusCode(200).
			when().
				get(boardID);
		System.out.println("API Call Ended...");
	}
	
}
