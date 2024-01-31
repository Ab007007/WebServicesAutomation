package com.synechron.restassured.training.globals;

import io.restassured.RestAssured;

public class ConfigurationClass {
	
	static {
		RestAssured.baseURI = "https://api.trello.com";
//		RestAssured.port = "8888";
		RestAssured.basePath = "1/boards/65b8926befbc0ef1ab5da901";
	}
	
}
