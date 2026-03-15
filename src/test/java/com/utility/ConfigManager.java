package com.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static Properties prop = new Properties();

	private static String filepath = "";

	private ConfigManager() {
	};

	static {

		String environement = System.getProperty("env", "qa");
		String environment = environement.toLowerCase().trim();

		switch (environment) {

		case "qa" -> filepath = "com.properties" + File.separator + "config.qa.properties";
		case "dev" -> filepath = "com.properties" + File.separator + "config.dev.properties";
		default -> filepath = "com.properties" + File.separator + "config.qa.properties";

		}

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);

		if (is == null)
			throw new RuntimeException("Can't find file at path");

		try {
			prop.load(is);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);

	}

}
