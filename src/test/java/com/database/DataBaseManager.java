package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.utility.ConfigManager;

public class DataBaseManager {

	private static final String Username = ConfigManager.getProperty("DB_USERNAME");
	private static final String URL = ConfigManager.getProperty("DB_URL");
	private static final String password = ConfigManager.getProperty("DB_PASSWORD");
	private static volatile Connection con;

	private DataBaseManager() {

	}

	public static void CreateConnection() {

		try {
			if (con == null) {//first check will all parallel threads enter

				synchronized (DataBaseManager.class) { //only one thread will enter
					if (con == null) {
						con = DriverManager.getConnection(URL, Username, password);
						System.out.println(con);
					}
				}

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
