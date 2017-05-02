package com.yp.sys.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yp.sys.service.rtu.IRtuService;

public class ManageCache {

		public  static Map<String,String> rtuPopertyReferMap=new HashMap<String,String>();
		@Autowired
		private IRtuService rtuService;
		//初始化
	public	void loadRtuPopertyReferMap(){
			rtuPopertyReferMap=rtuService.queryRtuPopertyReferMap();
		}
}
