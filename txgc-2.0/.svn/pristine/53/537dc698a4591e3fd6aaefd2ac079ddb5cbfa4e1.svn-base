package com.yp.sys.common;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import com.yp.sys.entity.Menu;
import com.yp.sys.pojo.UserBean;
import com.yp.sys.util.StringUtil;




/**   
 * 文件名称： 通用方法
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
public class GeneralMethod {
//	private static Logger logger = LoggerFactory.getLogger(GeneralMethod.class);
	
	/**
	 * @Description 设置记录通用属性
	 * @param obj 当前记录对象
	 * @param isAdd 是否是新增记录
	 */
	public static void setRecordInfo(Object obj,boolean isAdd) {
		UserBean currUser = GlobalContext.getCurrentUser();
		Class<?> clazz = obj.getClass();
		try {
			if(isAdd){
				Method setRecordStatusMethod = clazz.getMethod("setRecordStatus",String.class);
				setRecordStatusMethod.invoke(obj,GlobalConstant.FLAG_Y);
				Method setCreateTime = clazz.getMethod("setCreateTime",Date.class);
				setCreateTime.invoke(obj,new Date());
				Method setCreateUserId = clazz.getMethod("setCreateUserId",Long.class);
				if(currUser!=null){
					setCreateUserId.invoke(obj,currUser.getId());					
				}
			}
			Method setModifyTime = clazz.getMethod("setModifyTime",Date.class);
			setModifyTime.invoke(obj,new Date());
			Method setModifyUserId = clazz.getMethod("setModifyUserId",Long.class);
			if(currUser!=null){
				setModifyUserId.invoke(obj,currUser.getId());				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 校验按钮权限 
	 * @param url 当前页面uri
	 * @param flag 当前按钮标识,与easyui按钮的iconCls属性值一致
	 * @return boolean
	 */
	public static boolean checkButton (String url,String flag){
		final String FUNCTION_FLAG = "1";
		List<Menu> menus  = GlobalContext.getCurrentUser().getMenus();
		Long pid = null;
		String contextPath = GlobalContext.getRequest().getContextPath();
		for (Menu menu : menus) {
			if(StringUtil.isEquals(contextPath + menu.getSrc(), url)){
				pid = menu.getId();
				break;
			}
		}
		if(pid!=null){
			for (Menu menu : menus) {
				if(pid.equals(menu.getPid())&&StringUtil.isEquals(menu.getFunction(), FUNCTION_FLAG)&&StringUtil.isEquals(menu.getIconcls(), flag)){
					return true;
				}
			}
		}
		return false;
	}
}
