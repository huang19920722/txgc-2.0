package com.yp.sys.enums;

import com.yp.sys.common.BaseEnum;
import com.yp.sys.util.EnumUtil;
/**
 * 
 * 文件名称： 专业枚举类
 * 内容摘要： 
 * 创建人： zhangxiaoyan
 * 创建日期： 2015-7-7
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 *
 */
public enum ResourceEnum implements BaseEnum<String> {
	Public("Public","公开课"),
	Mk("Mk","慕课"),
	Wk("Wk","微课"),
	Layer("Layer","多媒体资源");
	private final String id;
	private final String name;
	
	ResourceEnum(String id,String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}
	public static String getNameById(String id) {
		return EnumUtil.getById(id, ResourceEnum.class).getName();
	}
}
