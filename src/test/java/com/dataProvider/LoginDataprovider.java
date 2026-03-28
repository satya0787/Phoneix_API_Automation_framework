package com.dataProvider;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.request.model.LoginDetails;
import  com.utility.CSVReaderUtility;

public class LoginDataprovider {
	
	
	@DataProvider(name="LoginCSVDataProvider")
	public Iterator<LoginDetails> getCSVData() {
		return CSVReaderUtility.getCSVData();
	}

}
