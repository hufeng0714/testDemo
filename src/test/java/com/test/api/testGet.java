package com.test.api;

import java.io.IOException;

import org.apache.http.ParseException;
import java.net.URL;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.test.client.RestfulClient;
import com.test.utils.JSONParser;

public class testGet {
	RestfulClient client;
	JSONObject responseBody;
	JSONParser jParser;
	int responseCode;
	String city;
	String url = "https://api.apishop.net/communication/phone/getLocationByPhoneNum?apiKey=anJBypUde088834d600f358ec0136b805aff3f51ff2660d&phoneNum=15800732429";
	
	
  @Test
  public void TestGetRequest() {
	//断言反馈中城市是否正确
	  Assert.assertEquals(city, "上海");
	//断言反馈中的状态码是否正确
	  Assert.assertEquals(responseCode, 200);
  }
  
  @BeforeClass
  public void beforeClass() throws ParseException,IOException{
	//发送请求，获取反馈
	  client = new RestfulClient();
	  client.getResponse(url);
	  responseBody = client.getBodyInJSON();
	  responseCode = client.getCodeInNumber();
	//调用JSONParser获取反馈中的城市信息
	  jParser = new JSONParser();
	  city = jParser.getCity(responseBody);
  }
}
