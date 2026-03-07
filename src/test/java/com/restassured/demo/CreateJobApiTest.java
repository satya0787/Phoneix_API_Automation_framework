package com.restassured.demo;

import org.testng.annotations.Test;

import com.constants.Roles;
import com.pojos.CreateJobDeatils;
import com.pojos.Customer;
import com.pojos.CustomerAddress;
import com.pojos.CustomerProduct;
import com.pojos.Problems;
import com.utility.ConfigManager2;
import com.utility.DynamicTokenGenerator;
import com.utility.SpecUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class CreateJobApiTest {

	@Test
	public void CreateJobAPitest() {

		Customer customer = new Customer("Raj", "kom", "9946471242", "", "test123@test.com");
		CustomerAddress custadrs = new CustomerAddress("304", "Jupiter", "MG road", "Bangur Nagar", "Goregaon West",
				"411039", "india", "telegana");
		CustomerProduct product = new CustomerProduct("2025-04-06T18:30:00.000Z", "13506113358841", "13506113358841",
				"13506113358841", "2025-04-06T18:30:00.000Z", 1, 1);

		Problems problems = new Problems(0, "displayissue");
		Problems[] problemArray = new Problems[1];

		problemArray[0] = problems;
		CreateJobDeatils payload = new CreateJobDeatils(0, 2, 1, 1, customer, custadrs, product, problemArray);

		Response response = given().spec(SpecUtility.requestSpecwithAuth(Roles.FD, payload))
				.log().all().when().post("/job/create").then().extract().response();

		System.out.println(response.asPrettyString());

	}

}
