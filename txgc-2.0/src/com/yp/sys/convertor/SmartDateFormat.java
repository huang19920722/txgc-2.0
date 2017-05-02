package com.yp.sys.convertor;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yp.sys.util.DateUtil;


/**   
 * 文件名称：自定义springMvc日期json转换器 
 * 内容摘要： 
 * 创建人： huangfei
 * 创建日期： 2015年6月10日
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 
public class SmartDateFormat extends SimpleDateFormat {  
    /***/
	private static final long serialVersionUID = -3383182944038504335L;

	@Override  
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {  
        return new StringBuffer(DateUtil.smartFormat(date));  
    }  
  
    @Override  
    public Date parse(String text) throws ParseException {  
        return DateUtil.smartFormat(text);  
    }  
} 
