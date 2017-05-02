package com.yp.sys.entity.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yp.sys.common.BaseEntity;

/**   
 * 文件名称：资源类型实体 
 * 内容摘要： 
 * 创建人： huangfei
 * 创建日期： 2015年6月5日
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
@Table(name="resource_type")
public class ResourceType extends BaseEntity {

	/***/
	private static final long serialVersionUID = 5635524245523141001L;
	
	/**类型名称*/
	private String name;
	
	/**唯一资源编号*/
	private String number;
	
	/**排序*/
	private Long sortNum;
	
	@Column(length=100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSortNum() {
		return sortNum;
	}
	public void setSortNum(Long sortNum) {
		this.sortNum = sortNum;
	}
	@Column(length=50,unique=true)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
