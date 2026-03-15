package com.api.dataDriven.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import com.dataproviders.api.bean.UserBean;
import com.utility.SpecUtility;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiDataDrivenTest {

	@Test(dataProvider = "LoginApiCSVDataProvider", dataProviderClass = com.dataproviders.DataProvidersutil.class)
	public void LoginTest(UserBean user) {

		given().spec(SpecUtility.requestSpec(user)).when().post("login").then().spec(SpecUtility.responseSpec_OK())
				.statusCode(200).body("message", equalTo("Success"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas//loginResponseSchema.json"));

	}

}
