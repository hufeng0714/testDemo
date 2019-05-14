package com.test.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class RestfulClient {
	
	CloseableHttpClient httpClient;
	HttpGet httpGet;
	CloseableHttpResponse httpResponse;
	int responseCode;
	JSONObject responseBody;
	HashMap<String,String> responseHeads;
	HttpPost httpPost;
	
    //ͨ��httpclient��ȡpost����ķ���
    public void sendPost(String url, List<NameValuePair> params, HashMap<String, String> headers) throws ClientProtocolException, IOException{
    	httpClient = HttpClients.createDefault();
        //����post�������
        httpPost = new HttpPost(url);

        //�������������ʽ
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        //����ͷ����Ϣ
        Set<String> set = headers.keySet();
        for(Iterator<String> iterator = set.iterator(); iterator.hasNext();){
            String key = iterator.next();
            String value = headers.get(key);
            httpPost.addHeader(key, value);
        }
        httpResponse = httpClient.execute(httpPost);
    }
	
	
	
	//ͨ��httpclient��ȡ����ķ���
	public void getResponse(String url) throws ClientProtocolException,IOException{
		httpClient = HttpClients.createDefault();
		httpGet = new HttpGet(url);
		httpResponse = httpClient.execute(httpGet);
	}
	
	  //��JSON��ʽ��ȡ������������
	public JSONObject getBodyInJSON() throws ParseException,IOException{
		HttpEntity entity;
		String entityToString;
		entity = httpResponse.getEntity();
		entityToString = EntityUtils.toString(entity);
		responseBody = JSON.parseObject(entityToString);
		
		System.out.println(responseBody);
		
		return responseBody;
	}
	
	//�Թ�ϣͼ�ķ�ʽ��ȡ������ͷ��
	public HashMap<String,String> getHeaderInHash(){
		Header[] headers;
		headers = httpResponse.getAllHeaders();
		
		responseHeads = new HashMap<String,String>();
		
		for(Header header:headers) {
			responseHeads.put(header.getName(), header.getValue());
		}
		
		System.out.println(responseHeads);
		
		return responseHeads;
	}
	
	//��ȡ����״̬��
	public int getCodeInNumber() {
		responseCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println(responseCode);
		return responseCode;
	}

}
