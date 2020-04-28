package com.cmcc.xpa.service.microservice.notes.utility;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONObjectUtil {

	/**
	 * 实体转换为JSON
	 * @param obj
	 * @return
	 */
	public static JSONObject entityToJSON(Object obj) {
		if (null == obj) {
			return null;
		}
		return (JSONObject) JSONObject.parse(JSONObject.toJSONString(obj,SerializerFeature.WriteMapNullValue));
	}
}
