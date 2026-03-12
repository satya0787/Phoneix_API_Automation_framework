package com.dataProvider;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobDeatils;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.constants.Product;
import com.github.javafaker.Faker;
import com.utility.DateTimeUtil;

public class CreateJobPayload {
	
	public static CreateJobDeatils generatepayload() {
		Faker faker = new Faker();
		String ime=faker.numerify("##############");
		Customer customer = new Customer(faker.name().firstName(),faker.name().lastName(), faker.phoneNumber().phoneNumber(), "", faker.internet().emailAddress(), "");
		CustomerAddress custadrs = new CustomerAddress(faker.address().buildingNumber(), faker.address().streetAddressNumber(), faker.address().streetAddress(), faker.address().cityName(),faker.address().state(),
				faker.numerify("#####"), faker.address().country(), faker.address().state());
		CustomerProduct product = new CustomerProduct(DateTimeUtil.getTimewithdaysAgo(10), ime,
				ime, ime, DateTimeUtil.getTimewithdaysAgo(10), Product.NEXUS_2.getCode(), 1);

		Problems problem = new Problems(1, "displayissue");
		List<Problems> problems = new ArrayList<Problems>();

		problems.add(problem);
		CreateJobDeatils payload = new CreateJobDeatils(0, 2, 1, 1, customer, custadrs, product, problems);
		return payload;

	}

}
