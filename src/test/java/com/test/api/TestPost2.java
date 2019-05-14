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
import com.test.utils.ExcelProcess;
import com.test.utils.JSONParser2;

public class TestPost2 extends TestApi{
	
	RestfulClient client;
	JSONObject responseBody;
	int responseCode;
	String url;
	String postBody;
	Object[][] excelData;
	HashMap<String,String> hashHead;
	
	@BeforeClass
	public void setup() throws ClientProtocolException,IOException{
		//读取用例excel
		excelData = ExcelProcess.proessExcel(excelPath, 1);
		//实例化client
		client = new RestfulClient();
		//设置好请求头部
		hashHead = new HashMap<String,String>();
		hashHead.put("Content-Type", "application/x-www-form-urlencoded");  
		
	}

	@Test
	public void testPostRequest() throws ClientProtocolException,IOException{
		 //从第二行开始遍历表单，跳过表头
		for(int i=1;i<excelData.length;i++) {
			//从特定位置读取测试数据
			String address = excelData[i][3].toString();
	        url = host+address;
	        String checkPoint = excelData[i][4].toString();
	        String checkValue = excelData[i][5].toString();
	        
	        //用NameValuePair存储所有请求参数
	        List<NameValuePair> keys = new ArrayList<NameValuePair>();
	        for(int j=7;j<excelData[i].length-2;j=j+2){
	            //因为每种请求的参数个数不确定，在这里进行非空判断
	            if(excelData[i][j]==null){
	                break;
	            }
	            NameValuePair pair = new BasicNameValuePair(excelData[i][j].toString(),excelData[i][j+1].toString());
	            keys.add(pair);             
	        }
	      
	      //发出请求
	        client.sendPost(url, keys, hashHead);

	        responseBody = client.getBodyInJSON();
	        responseCode = client.getCodeInNumber();

	        JSONParser2 jParser = new JSONParser2();
	        boolean result = jParser.isResponseCorrect(responseBody, checkPoint, checkValue);
	        
	        //断言判断结果
	        Assert.assertTrue(result);
	        Assert.assertEquals(responseCode, 200);
		}
	}
}
