package com.restassured.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static com.utility.ConfigManager.*;
import org.testng.annotations.Test;

import com.pojos.LoginDetails;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest{
	
	
    @Test
	public static void LoginTest() {

		LoginDetails payload = new LoginDetails("iamfd", "password");

		given().baseUri(getProperty("BASE_URI")).contentType(ContentType.JSON).body(payload)
				.accept(ContentType.ANY).log().all().when().post("login").then().log().all().statusCode(200)
				.body("message",equalTo("Success"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas//loginResponseSchema.json"));

	}

}
