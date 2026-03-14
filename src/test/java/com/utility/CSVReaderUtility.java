package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.api.request.model.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVReaderUtility {

	
	static String[] line=null;
	static List<LoginDetails> loginData;
	public static Iterator<LoginDetails> getCSVData(){
		

		File csvfile = new File(System.getProperty("user.dir") + "//src//test//resources//LoginDetails.csv");
		try {
			FileReader fr = new FileReader(csvfile);
			CSVReader csvreader = new CSVReader(fr);
			
			loginData = new ArrayList<>();
			csvreader.readNext();
			while((line=csvreader.readNext())!=null) {
				loginData.add(new LoginDetails(line[0],line[1]));
			}
			
			

		} catch (IOException | CsvException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return loginData.iterator();

	}

}
