package com.restassured.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import com.constants.Roles;
import com.dataProvider.CreateJobPayload;
import com.utility.SpecUtility;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {

	@Test
	public void CreateJobAPitest() {

		given().spec(SpecUtility.requestSpecwithAuth(Roles.FD, CreateJobPayload.generatepayload())).log().all().when()
				.post("/job/create").then().spec(SpecUtility.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas//CreateJobResponse.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_platform_id", equalTo(2))
				.body("data.job_number", startsWith("JOB_"));

	}

}
