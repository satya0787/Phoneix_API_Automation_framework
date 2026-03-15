package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobDeatils;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;
import com.utility.CreateJobBeanmapper;
import com.utility.CsvReaderUtil;

public class DataProvidersutil {

	@DataProvider(name = "LoginApiCSVDataProvider")
	public static Iterator<UserBean> loginDataProvider() {

		return CsvReaderUtil.loadCSV("testdata//LoginDetails.csv", UserBean.class);
	}

	
	
	
	@DataProvider(name = "CreatejobApiCSVDataProvider")
	public static Iterator<CreateJobDeatils> CreateJobdataProvider() {
		Iterator<CreateJobBean> iterator = CsvReaderUtil.loadCSV("testdata//CreatjobPayload.csv", CreateJobBean.class);
		CreateJobBean tempbean;
		CreateJobDeatils temppayload;
		List<CreateJobDeatils> payloads = new ArrayList<>();
		while (iterator.hasNext()) {
			tempbean = iterator.next();
			temppayload = CreateJobBeanmapper.mapper(tempbean);
			payloads.add(temppayload);
		}

		return payloads.iterator(); 
	}

}
