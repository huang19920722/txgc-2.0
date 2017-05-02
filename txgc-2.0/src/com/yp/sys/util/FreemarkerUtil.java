package com.yp.sys.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**   
 * 文件名称：freemarker工具类 
 * 内容摘要： 
 * 创建人： huangfei
 * 创建日期： 2015年12月29日
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 
public class FreemarkerUtil {
	/**
	* 获取freemarker可使用的bean
	* @param clz 类型
	* @return
	*/
	@SuppressWarnings("rawtypes")
	public static TemplateModel getStaticModel(Class clz) {
	      BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
	try {
	      return wrapper.getStaticModels().get(clz.getName());
	    } catch (TemplateModelException e) {
	      e.printStackTrace();
	    }
	      return null;
	    }

}
