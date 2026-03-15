package com.api.dataDriven.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import com.api.request.model.CreateJobDeatils;
import com.constants.Roles;
import com.dataProvider.CreateJobPayload;
import com.dataproviders.api.bean.CreateJobBean;
import com.utility.SpecUtility;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {

	@Test(description="Creatinng job",dataProvider="CreatejobApiCSVDataProvider",dataProviderClass = com.dataproviders.DataProvidersutil.class)
	public void CreateJobAPitest(CreateJobDeatils crbean) {

		given().spec(SpecUtility.requestSpecwithAuth(Roles.FD,crbean)).log().all().when()
				.post("/job/create").then().spec(SpecUtility.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas//CreateJobResponse.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_platform_id", equalTo(2))
				.body("data.job_number", startsWith("JOB_"));

	}

}
