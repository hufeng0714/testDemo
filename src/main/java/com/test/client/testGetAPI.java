package com.test.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class testGetAPI {
	
	public static void main(String[] args) throws ClientProtocolException,IOException {
		String url = "http://api-istage.haomaiche.com/ware/car/310000/c74216cf2f4441c1b870b060c6ae97c1/car-type?time=1556522969505&source=102";
		CloseableHttpClient httpClient;
		HttpGet httpGet;
		CloseableHttpResponse httpResponse;
		
		int responseCode;
		HttpEntity responseBody ;		
		Header[] responseHeader;
		
		httpClient = HttpClients.createDefault();
		httpGet = new HttpGet(url);
		httpResponse = httpClient.execute(httpGet);
		
		responseCode = httpResponse.getStatusLine().getStatusCode();
		responseBody = httpResponse.getEntity();
		responseHeader = httpResponse.getAllHeaders();
		
		String responseBodyString = EntityUtils.toString(responseBody,"UTF-8");
		
		HashMap<String,String> hashMap = new HashMap<String,String>();
		for(Header header:responseHeader) {
			hashMap.put(header.getName(),header.getValue());
		}
		
		
		System.out.println(responseCode);
		System.out.println("---------");
		System.out.println(responseBodyString);
		System.out.println("---------");
		System.out.println(hashMap);
				
	}
}
