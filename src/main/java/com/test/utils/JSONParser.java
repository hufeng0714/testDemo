package com.test.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

public class JSONParser {
	JSONObject internalJSON;
	
	public String getCity(JSONObject jo) {
		
		String city = "";
		
		try {
			//�Ȼ�ȡ�����е�result���һ���ڲ�JSON����
            JSONObject internalJSON = jo.getJSONObject("result");
            //�ٸ��ݼ������Ҽ�ֵ
            city = internalJSON.getString("city") ;
        }catch (Exception e){
            e.printStackTrace();
        }		
		return city;
	}

}
