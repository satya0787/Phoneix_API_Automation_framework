package com.utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static com.utility.ConfigManager2.*;

import com.pojos.LoginDetails;

public class DynamicTokenGenerator {

	public static String getToken() {
		LoginDetails payload = new LoginDetails("iamfd", "password");

		String token = RestAssured.given().baseUri(getProperty("BASE_URI")).contentType(ContentType.JSON)
				.accept(ContentType.ANY).body(payload).when().post("login").then().extract().jsonPath()
				.getString("data.token");

		return token;

	}

}
