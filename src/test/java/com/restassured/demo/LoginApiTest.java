package com.restassured.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.request.model.LoginDetails;
import com.utility.SpecUtility;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest{
	
	
    @Test
	public static void LoginTest() {

		LoginDetails payload = new LoginDetails("iamfd", "password");

		given()
		.spec(SpecUtility.requestSpec(payload))
		.when()
		.post("login")
		.then().spec(SpecUtility.responseSpec_OK()).statusCode(200)
				.body("message",equalTo("Success"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas//loginResponseSchema.json"));

	}

}
