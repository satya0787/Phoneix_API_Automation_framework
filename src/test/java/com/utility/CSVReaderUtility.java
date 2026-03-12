package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.api.request.model.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVReaderUtility {

	public static Iterator<LoginDetails> getCSVData(){

		List<LoginDetails> loginData = new ArrayList<>();
		File csvfile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "LoginDetails.csv");
		if (!csvfile.exists() || !csvfile.isFile()) {
			System.out.println("CSV file not found: " + csvfile.getAbsolutePath());
			return Collections.emptyIterator();
		}

		try (FileReader fr = new FileReader(csvfile);
			 CSVReader csvreader = new CSVReader(fr)) {

			// skip header if present
			csvreader.readNext();
			String[] line;
			while ((line = csvreader.readNext()) != null) {
				// skip empty or malformed rows
				if (line.length < 2) {
					// ignore rows that don't have at least 2 columns
					continue;
				}
				String username = line[0] == null ? "" : line[0].trim();
				String password = line[1] == null ? "" : line[1].trim();
				if (username.isEmpty() && password.isEmpty()) {
					// skip rows where both fields are empty
					continue;
				}
				loginData.add(new LoginDetails(username, password));
			}

		} catch (IOException | CsvException e) {
			System.out.println("Error reading CSV: " + e.getMessage());
			e.printStackTrace();
			return Collections.emptyIterator();
		}

		if (loginData.isEmpty()) {
			return Collections.emptyIterator();
		}
		return loginData.iterator();

	}

}