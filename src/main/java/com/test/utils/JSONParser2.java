package com.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import net.minidev.json.JSONArray;

public class JSONParser2 {

	public boolean isResponseCorrect(JSONObject jo,String checkPoint,String passValue) {
		//��jsonpath����json����ȡresult���ض���ֵ
		ReadContext context = JsonPath.parse(jo);
		JSONArray result = context.read("$.result.."+checkPoint);
		String resultString = result.get(0).toString();
		
		if(resultString.equals(passValue)) {
			return true;
		}else {
			return false;
		}
	}
}
