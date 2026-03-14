package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.utility.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBaseManager_Hk {

	private static final String Username = ConfigManager.getProperty("DB_USERNAME");
	private static final String URL = ConfigManager.getProperty("DB_URL");
	private static final String password = ConfigManager.getProperty("DB_PASSWORD");
	private static final int MaximumPoolSize = Integer.parseInt(ConfigManager.getProperty("MaximumPoolSize"));
	private static final int ConnectionTimeout = Integer.parseInt(ConfigManager.getProperty("ConnectionTimeout"));
	private static final int MinimumIdle = Integer.parseInt(ConfigManager.getProperty("MinimumIdle"));
	private static final int IdleTimeout = Integer.parseInt(ConfigManager.getProperty("IdleTimeout"));
	private static final int MaxLifetime = Integer.parseInt(ConfigManager.getProperty("MaxLifetime"));

	private static HikariConfig hikariconfig;
	private static volatile HikariDataSource hikarids;
	private static Connection con;

	private DataBaseManager_Hk() {

	}

	public static void initializepool() {
		if (hikarids == null) {// first check will all parallel threads enter

			synchronized (DataBaseManager_Hk.class) { // only one thread will enter
				if (hikarids == null) {
					hikariconfig = new HikariConfig();
					hikariconfig.setJdbcUrl(URL);
					hikariconfig.setUsername(Username);
					hikariconfig.setPassword(password);
					hikariconfig.setMaximumPoolSize(MaximumPoolSize);
					hikariconfig.setMinimumIdle(MinimumIdle);
					hikariconfig.setConnectionTimeout(ConnectionTimeout);
					hikariconfig.setIdleTimeout(IdleTimeout);
					hikariconfig.setMaxLifetime(MaxLifetime);

					hikarids = new HikariDataSource(hikariconfig);
				}
			}

		}
	}

	public static Connection getConnection() throws SQLException {
		if (hikarids == null) {
			initializepool();
		} else if (hikarids.isClosed()) {
			throw new SQLException("Hikar DATA source is closed");
		}

		con = hikarids.getConnection();

		return con;

	}

}
