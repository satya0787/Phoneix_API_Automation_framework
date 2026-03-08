package com.restassured.demo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.constants.Roles;
import com.pojos.CreateJobDeatils;
import com.pojos.Customer;
import com.pojos.CustomerAddress;
import com.pojos.CustomerProduct;
import com.pojos.Problems;

import com.utility.SpecUtility;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {

	@Test
	public void CreateJobAPitest() {

		Customer customer = new Customer("Raj", "kom", "9946471242", "", "test123@test.com", "");
		CustomerAddress custadrs = new CustomerAddress("304", "Jupiter", "MG road", "Bangur Nagar", "Goregaon West",
				"500055", "india", "telegana");
		CustomerProduct product = new CustomerProduct("2025-04-08T18:30:00.000Z", "18606113358046", "18606113358046",
				"18606113358046", "2025-04-08T18:30:00.000Z", 1, 1);

		Problems problem = new Problems(1, "displayissue");
		List<Problems> problems = new ArrayList<Problems>();

		problems.add(problem);
		CreateJobDeatils payload = new CreateJobDeatils(0, 2, 1, 1, customer, custadrs, product, problems);

		given().spec(SpecUtility.requestSpecwithAuth(Roles.FD, payload)).log().all().when().post("/job/create").then()
				.spec(SpecUtility.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas//CreateJobResponse.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_platform_id", equalTo(2))
				.body("data.job_number", startsWith("JOB_"));

	}

}
