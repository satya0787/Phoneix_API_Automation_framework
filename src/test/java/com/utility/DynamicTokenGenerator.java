package com.utility;

import static com.utility.ConfigManager2.getProperty;

import com.api.request.model.LoginDetails;
import com.constants.Roles;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DynamicTokenGenerator {

	public static String getToken(Roles role) {

		LoginDetails payload;

		switch (String.valueOf(role)) {
		case "FD":
			payload = new LoginDetails("iamfd", "password");
			break;
		case "SUP":
			payload = new LoginDetails("iamsp", "password");
			break;
		case "QA":
			payload = new LoginDetails("iamsp", "password");
			break;
		default:
			payload = new LoginDetails("iamfd", "password");

		}

		String token = RestAssured.given().baseUri(getProperty("BASE_URI")).contentType(ContentType.JSON)
				.accept(ContentType.ANY).body(payload).when().post("login").then().extract().jsonPath()
				.getString("data.token");

		return token;

	}

}
