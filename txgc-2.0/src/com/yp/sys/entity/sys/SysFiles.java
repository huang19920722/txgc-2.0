package com.yp.sys.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.yp.sys.common.BaseEntity;
import com.yp.sys.common.GlobalConstant;

/**   
 * 文件名称：系统附件实体 
 * 内容摘要： 
 * 创建人： huangfei
 * 创建日期： 2015年6月18日
 * 版本号： v1.0.0
 * 公  司：重庆重邮汇侧有限公司
 * 版权所有： (C)2001-2015     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 
@Entity
@Table(name="sys_files")
@Where(clause="recordStatus='"+GlobalConstant.FLAG_Y+"'")
public class SysFiles extends BaseEntity {

	/***/
	private static final long serialVersionUID = -2300867968083933362L;
	
	/**附件名称*/
	private String name;
	
	
	/**文件路径*/
	private String filePath;
	
	/**文件大小*/
	private String size;
	/**时长*/
	private Long time;
	
	/**发布人*/
	private String createUserName;
	
	@Column(length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=200)
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(length=20)
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Transient
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	@Transient
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	private String dateTime;
	@Transient
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
