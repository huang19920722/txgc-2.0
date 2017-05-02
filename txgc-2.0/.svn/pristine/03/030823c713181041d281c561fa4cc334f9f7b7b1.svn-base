package com.yp.sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;


/**   
 * 文件名称： 日期工具类
 * 内容摘要： 
 * 创建人： huangfei
 * 创建日期： 2015年5月11日
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 
public class DateUtil {
	
	//日期格式
	public static final String Y_M_D = "yyyy-MM-dd";  
    public static final String Y_M_D_HM = "yyyy-MM-dd HH:mm";  
    public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";  
    public static final String YMD = "yyyyMMdd";  
    public static final String YMDHM = "yyyyMMddHHmm";  
    public static final String YMDHMS = "yyyyMMddHHmmss";  
    public static final String ymd = "yyyy/MM/dd";  
    public static final String ymd_HM = "yyyy/MM/dd HH:mm";  
    public static final String ymd_HMS = "yyyy/MM/dd HH:mm:ss";  

	/**
	 * @Description 将Date类型转换为字符串
	 * @param date 日期类型
	 * @return String 日期字符串,格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String format(Date date) {
		return format(date, Y_M_D_HMS);
	}

	/**
	 * @Description 将Date类型转换为指定格式字符串
	 * @param date 日期类型
	 * @param pattern 字符串格式 默认yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "null";
		}
		if (StringUtil.isEmpty(pattern) || StringUtil.isBlank(pattern) || StringUtil.isEquals("null", pattern)) {
			pattern = Y_M_D_HMS;
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * @Description 将yyyy-MM-dd HH:mm:ss格式字符串转换成日期
	 * @param dateString
	 * @return Date
	 */
	public static Date format(String dateString) {
		return format(dateString, null);
	}

	/**
	 * @Description 将字符串转换为Date类型
	 * @param dateString 日期字符串
	 * @param pattern 格式
	 * @return Date
	 */
	public static Date format(String dateString, String pattern) {
		if (StringUtil.isEmpty(pattern) || StringUtil.isBlank(pattern) || StringUtil.isEquals("null", pattern)) {
			pattern = Y_M_D_HMS;
		}
		if (StringUtil.isEmpty(dateString) || StringUtil.isBlank(dateString) || StringUtil.isEquals("null", dateString)) {
			return null;
		}
		Date d = null;
		try {
			d = new SimpleDateFormat(pattern).parse(dateString);
		} catch (ParseException pe) {
			throw new RuntimeException("日期转换异常："+dateString);
		}
		return d;
	}
	/**
	 * @Description 获取当前时间，格式:yyyyMMddHHmmss
	 * @return String
	 */
	public static String getCurrDateTime() {
		return DateFormatUtils.format(System.currentTimeMillis(),YMDHMS);
	}
	/**
	 * @Description 获取当前日期，格式:yyyy-MM-dd
	 * @return String
	 */
	public static String getCurrDate() {
		return DateFormatUtils.format(System.currentTimeMillis(),Y_M_D);
	}
	/**
	 * 获取当前日期 
	 * @param pattern 格式
	 * @return String
	 */
	public static String getCurrDate(String pattern) {
		return DateFormatUtils.format(System.currentTimeMillis(),pattern);
	}
	/** 
     * 智能将日期转成字符串 
     * @param date 
     * @return 
     */  
    public static String smartFormat(Date date) {  
        String dateStr = null;  
        if (date == null) {  
            dateStr = "";  
        } else {  
            try {  
                dateStr = format(date, Y_M_D_HMS);  
                //时分秒  
                if (dateStr.endsWith(" 00:00:00")) {  
                    dateStr = dateStr.substring(0, 10);  
                }  
                //时分  
                else if (dateStr.endsWith("00:00")) {  
                    dateStr = dateStr.substring(0, 16);  
                }  
                //秒  
                else if (dateStr.endsWith(":00")) {  
                    dateStr = dateStr.substring(0, 16);  
                }  
            } catch (Exception ex) {  
                throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);  
            }  
        }  
        return dateStr;  
    }  
  
    /** 
     * 智能将字符串转成日期
     * @param text 
     * @return 
     */  
    public static Date smartFormat(String text) {  
        Date date = null;  
        try {  
            if (text == null || text.length() == 0) {  
                date = null;  
            } else if (text.length() == 10) {  
                date = format(text, Y_M_D);  
            } else if (text.length() == 13) {  
                date = new Date(Long.parseLong(text));  
            } else if (text.length() == 16) {  
                date = format(text, Y_M_D_HM);  
            } else if (text.length() == 19) {  
                date = format(text, Y_M_D_HMS);  
            } else {  
                throw new IllegalArgumentException("日期长度不符合要求!");  
            }  
        } catch (Exception e) {  
            throw new IllegalArgumentException("日期转换失败!");  
        }  
        return date;  
    } 
    
    /**
	 * 计算指定时间相隔的天、小时、分钟
	 * @param d1 较晚时间（格式：yyyyMMddHHmmss）
	 * @param d2 较早时间（格式：yyyyMMddHHmmss）
	 * @return
	 */
	public static String signBetweenTowDate(Date d2) {
		Date d1 = new Date();
		long year =  (d1.getTime() - d2.getTime()) / (3600L * 24 * 1000 * 30 * 12);
		long month =  (d1.getTime() - d2.getTime()) / (3600L * 24 * 1000 * 30);
		long day =  (d1.getTime() - d2.getTime()) / (3600L * 24 * 1000);
		long hour =  (d1.getTime() - d2.getTime()) / (3600L  * 1000);
		long minutes =  (d1.getTime() - d2.getTime()) / ( 60L * 1000);
		if(year>0){
			return year+"年前";
		}else if(month>0){
			return month+"个月前";
		}else if(day>0){
			return day+"天前";
		}else if(hour>0){
			return hour+"小时前";
		}else if(minutes>0){
			return minutes+"分钟前";
		}else{
			return "刚刚";
		}
	}
	/**
	 * 获取某月的最后一天
	 * @Title:getLastDayOfMonth
	 * @Description:
	 * @param:@param year
	 * @param:@param month
	 * @param:@return
	 * @return:String
	 * @throws
	 */
	public static String getLastDayOfMonth(int year,int month)
	{
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		
		return lastDayOfMonth;
	}
	

}
