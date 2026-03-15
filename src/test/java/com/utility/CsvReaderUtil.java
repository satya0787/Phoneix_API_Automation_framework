package com.utility;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReaderUtil {

	public static <T> Iterator<T> loadCSV(String path, Class<T> bean) {
		List<T> list = null;
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		try (InputStreamReader ir = new InputStreamReader(is); CSVReader cr = new CSVReader(ir);

		) {

			CsvToBean<T> csvtobean = new CsvToBeanBuilder<T>(cr).withType(bean).withIgnoreEmptyLine(true).build();
			list = csvtobean.parse();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return list.iterator();
	}

}
