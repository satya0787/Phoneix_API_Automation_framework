package com.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveFilter implements Filter {

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		// TODO Auto-generated method stub
		
		reducted(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		
		
		return response;
	}

	private void reducted(FilterableRequestSpecification requestSpec) {
	     String payload =requestSpec.getBody().toString();
	     
	     String masked=payload.replaceAll("\"password\"\\s*:\\s*\"[^\"]+\"", "\"password\":\"[Redacted]\"");
	     //payload.replaceAll("\"password\"\\s*:\\s*\"[^\"]+\"", "\"password\":\"[Reducted]\"");
	     System.out.println(masked);
		
	}

}
