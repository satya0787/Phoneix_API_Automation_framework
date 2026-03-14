package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.utility.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCpDemo {
	private static Connection con;
	private static ResultSet resultset;
	private static Statement statement;

	public static void main(String[] args) {

		HikariConfig hikariconfig = new HikariConfig();
		hikariconfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariconfig.setUsername(ConfigManager.getProperty("DB_USERNAME"));
		hikariconfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariconfig.setMaximumPoolSize(10);
		hikariconfig.setMinimumIdle(2);
		hikariconfig.setConnectionTimeout(10000);
		hikariconfig.setIdleTimeout(10000);
		hikariconfig.setMaxLifetime(180000);

		HikariDataSource hikarids = new HikariDataSource(hikariconfig);
		try {
			con = hikarids.getConnection();
			statement = con.createStatement();
			resultset = statement.executeQuery("SELECT first_name ,last_name ,mobile_number from tr_customer;");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(con);
		try {
			while (resultset.next()) {
				System.out.println(resultset.getString("first_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
