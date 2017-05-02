package com.yp.sys.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 文件名称: 时间转换
 * 内容摘要: 
 * 创 建 人:
 * 版权所有: 版权所有(C)2001-2004
 * 
 * 修改记录1: 
 *   修改日期：
 *   版 本 号：
 *   修 改 人：
 *   修改内容：
 * 修改记录2：…
 * 
 */
public class TimeStampUtil {

	public String timeStampToString(Timestamp ts){
		String newString=new String(); 
		 try {
			newString=ts.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return newString;
	}
	public Timestamp stringToTimeStame(String str){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		 Date date = new Date();   
		 String dateStr = new String();
		 DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	 try {
		  	date = sdf.parse(str); 
		  	dateStr = sdf.format(date);
			ts = Timestamp.valueOf(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}
}
