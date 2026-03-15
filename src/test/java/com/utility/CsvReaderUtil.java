package com.utility;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReaderUtil {

	/*
	 * 
	 * Reads data from CSV file
	 * 
	 * 
	 */

	private CsvReaderUtil() {
	};

	private static CsvToBean<UserBean> csvTobean;
	private static List<UserBean> userlist;

	public static Iterator<UserBean> loadcsv(String pathOfCSVFile) {
		// no leading slash when using ClassLoader
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		// Use try-with-resources to ensure streams are closed and avoid NPE when
		// resource missing
		try (InputStream is = cl.getResourceAsStream(pathOfCSVFile)) {
			if (is == null) {
				System.err.println("Resource not found on classpath: " + pathOfCSVFile);

			}

			try (InputStreamReader ir = new InputStreamReader(is); CSVReader csvReader = new CSVReader(ir)) {

				csvTobean = new CsvToBeanBuilder<UserBean>(csvReader).withType(UserBean.class).withIgnoreEmptyLine(true)
						.build();

				userlist = csvTobean.parse();

				System.out.println("Parsed records: " + userlist.size());
				for (UserBean entry : userlist) {
					System.out.println(entry);
				}

			}
		} catch (Exception e) {
			System.err.println("Error reading CSV resource: " + e.getMessage());
			e.printStackTrace();
		}
		return userlist.iterator();
	}

}