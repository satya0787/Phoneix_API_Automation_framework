package com.utility;

import org.hamcrest.Matchers;

import com.constants.Roles;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtility {

	public static RequestSpecification requestSpec(Object payload) {

		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager2.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.ANY).setBody(payload).log(LogDetail.METHOD).log(LogDetail.BODY)
				.build();

		return request;
	}
	
	
	public static RequestSpecification requestSpecwithAuth(Roles role) {

		
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager2.getProperty("BASE_URI"))
				.addHeader("Authorization",DynamicTokenGenerator.getToken(role))
				.setContentType(ContentType.JSON).setAccept(ContentType.ANY).log(LogDetail.METHOD).log(LogDetail.BODY)
				.build();

		return request;
	}
	
public static RequestSpecification requestSpecwithAuthAndText(Roles role) {

		
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager2.getProperty("BASE_URI"))
				.addHeader("Authorization",DynamicTokenGenerator.getToken(role))
				.setContentType(ContentType.TEXT).setAccept(ContentType.ANY).log(LogDetail.METHOD).log(LogDetail.BODY)
				.build();

		return request;
	}

	

	
	
	
	
	public static ResponseSpecification responseSpec_OK() {
	ResponseSpecification response =	new ResponseSpecBuilder().expectContentType(ContentType.JSON)
		    .expectResponseTime(Matchers.lessThan(1000l))
		    .expectStatusCode(200)
		    .log(LogDetail.ALL)
		    .build();
	return response;
	}

}
