package com.utility;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.api.request.model.LoginDetails;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReaderUtil {

    public static void main(String[] args) {
        String resourceName = "LoginDetails.csv"; // no leading slash when using ClassLoader
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        // Use try-with-resources to ensure streams are closed and avoid NPE when resource missing
        try (InputStream is = cl.getResourceAsStream(resourceName)) {
            if (is == null) {
                System.err.println("Resource not found on classpath: " + resourceName);
                return;
            }

            try (InputStreamReader ir = new InputStreamReader(is);
                 CSVReader csvReader = new CSVReader(ir)) {

                CsvToBean<LoginDetails> csvTobean = new CsvToBeanBuilder<LoginDetails>(csvReader)
                        .withType(LoginDetails.class)
                        .withIgnoreEmptyLine(true)
                        .build();

                List<LoginDetails> userlist = csvTobean.parse();

                System.out.println("Parsed records: " + userlist.size());
                for (LoginDetails entry : userlist) {
                    System.out.println(entry);
                }

            }
        } catch (Exception e) {
            System.err.println("Error reading CSV resource: " + e.getMessage());
            e.printStackTrace();
        }
    }

}