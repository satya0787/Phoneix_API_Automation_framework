package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DataBaseManager;
import com.database.DataBaseManager_Hk;

public class CreateJobPayloadDao {

	
		private static final String SQL_QUERY = 
				"""
				SELECT 
mst_oem_id,
mst_platform_id,
mst_warrenty_status_id,
mst_service_location_id,
first_name,
last_name,
mobile_number,
mobile_number_alt,
email_id,
email_id_alt,
flat_number,
apartment_name,
street_name,
area,
landmark,
country,
pincode,
state,
mst_model_id,
dop,
imei2,
imei1,
serial_number,
mst_problem_id,
remark

FROM tr_customer 
INNER JOIN tr_customer_address 
ON tr_customer.tr_customer_address_id = tr_customer_address.id 
INNER JOIN tr_customer_product
ON tr_customer_product.id = tr_customer.tr_customer_address_id
INNER JOIN tr_job_head 
ON tr_job_head.tr_customer_id  = tr_customer.id
INNER JOIN map_job_problem
ON map_job_problem.tr_job_head_id = tr_job_head.id 
LIMIT 5;
				""";
		
		

public static void getcreateJobPayload(){
	
	Connection connection;
	try {
		connection = DataBaseManager_Hk.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rset =statement.executeQuery(SQL_QUERY);
		while(rset.next()) {
			System.out.println(rset.getString("first_name"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}

}
