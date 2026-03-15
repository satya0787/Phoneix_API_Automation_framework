package com.utility;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobDeatils;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobBeanmapper {

	private CreateJobBeanmapper() {
	};

	public static CreateJobDeatils mapper(CreateJobBean bean) {

		int Mst_service_location_id = Integer.parseInt(bean.getMst_service_location_id());
		int Mst_platform_id = Integer.parseInt(bean.getMst_platform_id());
		int Mst_oem_id = Integer.parseInt(bean.getMst_oem_id());
		int Mst_warrenty_status_id = Integer.parseInt(bean.getMst_warrenty_status_id());
		int productid = Integer.parseInt(bean.getCustomer_product__product_id());
		int modelid = Integer.parseInt(bean.getCustomer_product__mst_model_id());

		Customer customer = new Customer(bean.getCustomer__first_name(), bean.getCustomer__last_name(),
				bean.getCustomer__mobile_number(), bean.getCustomer__mobile_number_alt(), bean.getCustomer__email_id(),
				bean.getCustomer__email_id_alt());

		CustomerAddress custadrs = new CustomerAddress(bean.getCustomer_address__flat_number(),
				bean.getCustomer_address__apartment_name(), bean.getCustomer_address__landmark(),
				bean.getCustomer_address__street_name(), bean.getCustomer_address__area(),
				bean.getCustomer_address__pincode(), bean.getCustomer_address__state(),
				bean.getCustomer_address__country());

		CustomerProduct product = new CustomerProduct(bean.getCustomer_product__dop(),
				bean.getCustomer_product__serial_number(), bean.getCustomer_product__imei1(),
				bean.getCustomer_product__imei2(), bean.getCustomer_product__popurl(), productid, modelid);

		Problems problem = new Problems(Integer.parseInt(bean.getProblems__id()), bean.getProblems__remark());
		List<Problems> problems = new ArrayList<Problems>();

		problems.add(problem);

		CreateJobDeatils payload = new CreateJobDeatils(Mst_service_location_id, Mst_platform_id,
				Mst_warrenty_status_id, Mst_oem_id, customer, custadrs, product, problems);

		
		
		return payload;
	}

}
