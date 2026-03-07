package com.restassured.demo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.constants.Roles;
import com.utility.SpecUtility;

public class MasterApiTest {

	@Test
	public void MasterAPitest() {

		given().spec(SpecUtility.requestSpecwithAuthAndText(Roles.FD)).when().post("master").then()
				.spec(SpecUtility.responseSpec_OK());

	}

}
