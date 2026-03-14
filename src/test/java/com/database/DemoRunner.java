package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Connection con =DataBaseManager_Hk.getConnection();
		System.out.println(con);
	}

}
