package com.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager2 {

	private static Properties prop = new Properties();
	private static String path = "com.properties" + File.separator + "config.qa.properties.properties";
	private static String env;

	private ConfigManager2() {
	};

	static {

		env = System.getProperty("env", "qa");
		switch (env.toLowerCase().trim()) {

		case "dev" -> path = "com.properties" + File.separator + "config.dev.properties";

		case "qa" -> path = "com.properties" + File.separator + "config.qa.properties";

		case "prod" -> path = "com.properties" + File.separator + "config.prod.properties";

		default -> path = "com.properties" + File.separator + "config.qa.properties.properties";

		}
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (input == null) {
			throw new RuntimeException("can't find the file at path" + path);
		}
		try {

			prop.load(input);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);

	}

}
