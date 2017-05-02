package com.yp.sys.enums;


import com.yp.sys.common.BaseEnum;
import com.yp.sys.util.EnumUtil;
/**
 * 
 * 文件名称： 用户类型枚举
 * 内容摘要： 用户类型枚举类
 * 创建人： lanxiaowei
 * 创建日期： 2015-6-17
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
public enum UserTypeEnum implements BaseEnum<String> {
	Manager("Manager","管理员"),
	ManaTeacher("ManaTeacher","教师管理员"),
	Student("Student","学生"),
	Teacher("Teacher","教师");
	private String id;
	private String name;
	UserTypeEnum(String id,String name){
		this.id=id;
		this.name=name;
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public String getName() {
		return name;
	}
	public static String getNameById(String id) {
		return EnumUtil.getById(id, UserTypeEnum.class).getName();
	}

}
