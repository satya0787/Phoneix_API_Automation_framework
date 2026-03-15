package com.dataproviders;

import java.io.File;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.dataproviders.api.bean.UserBean;
import com.utility.CsvReaderUtil;

public class DataProvidersutil {

	@DataProvider(name = "LoginApiCSVDataProvider")
	public static Iterator<UserBean> loginDataProvider() {

		return CsvReaderUtil.loadcsv("testdata//LoginDetails.csv");
	}

}
