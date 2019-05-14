package com.test.api;

import java.io.FileInputStream;
import java.util.Properties;

public class TestApi {

	public Properties prop;
	public String excelPath;
	public String host;
	
	public TestApi() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		host = prop.getProperty("Host");
		excelPath = prop.getProperty("testData");
	}
}
