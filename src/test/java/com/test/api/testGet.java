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
	//���Է����г����Ƿ���ȷ
	  Assert.assertEquals(city, "�Ϻ�");
	//���Է����е�״̬���Ƿ���ȷ
	  Assert.assertEquals(responseCode, 200);
  }
  
  @BeforeClass
  public void beforeClass() throws ParseException,IOException{
	//�������󣬻�ȡ����
	  client = new RestfulClient();
	  client.getResponse(url);
	  responseBody = client.getBodyInJSON();
	  responseCode = client.getCodeInNumber();
	//����JSONParser��ȡ�����еĳ�����Ϣ
	  jParser = new JSONParser();
	  city = jParser.getCity(responseBody);
  }
}
