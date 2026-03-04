package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	private static Properties prop = new Properties();

	private ConfigManager() {
	};

	static {
		File propfile = new File(
				System.getProperty("user.dir") + "//src//test//resources//com.properties//LoginProperties.properties");
		try {
			FileReader fr = new FileReader(propfile);
			prop.load(fr);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);

	}

}
