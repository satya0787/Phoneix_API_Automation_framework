package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JOBDemo {
	public static void main(String[] args) throws SQLException {

		Connection connection = DriverManager.getConnection("jdbc:mysql://64.227.160.186 :3306/SR_DEV", "srdev_ro_automation",
				"Srdev@123");

		Statement statement = connection.createStatement();

		ResultSet resultset = statement.executeQuery("SELECT first_name ,last_name ,mobile_number from tr_customer;");

		while (resultset.next()) {
			String first_name = resultset.getString("first_name");
			String last_name = resultset.getString("last_name");
			String mobile_number = resultset.getString("mobile_number");

			System.out.println(first_name + "|" + mobile_number + "|" + last_name);
		}

	}

}
