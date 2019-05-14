package com.test.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.test.client.RestfulClient;
import com.test.utils.JSONParser;

public class testPost extends TestApi{

	RestfulClient client;
	JSONObject responseBody;
	JSONParser jParser;
	int responseCode;
	String city;
	String url = "https://api.apishop.net/communication/phone/getLocationByPhoneNum";
	String postBody;
	
	@Test
	public void testPostRequest() {
		//���Է����г�����Ϣ�Ƿ���ȷ
	      Assert.assertEquals(city, "�Ϻ�");
	    //���Է�����״̬���Ƿ���ȷ
	      Assert.assertEquals(responseCode, 200);
	}
	
	@BeforeClass
	public void beforeClass() throws ClientProtocolException, IOException {
	      client = new RestfulClient();

	      //��NameValuePair��list����������������
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("apiKey", "anJBypUde088834d600f358ec0136b805aff3f51ff2660d"));
	        params.add(new BasicNameValuePair("phoneNum", "15800732429"));
	        
	        //�ù�ϣͼ׼������ͷ����Ϣ
	        HashMap<String, String> hashHead = new HashMap<String, String>();
	        hashHead.put("Content-Type", "application/x-www-form-urlencoded");
	        
	        //���η���post���󲢽��շ���
	        client.sendPost(url, params, hashHead);

	        responseBody = client.getBodyInJSON();
	        responseCode = client.getCodeInNumber();
	        
	        System.out.println(responseBody);
	        jParser = new JSONParser();
	        city = jParser.getCity(responseBody);
	    }
}
