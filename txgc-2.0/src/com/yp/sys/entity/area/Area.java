package com.yp.sys.entity.area;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**   
 * 文件名称：资源类型实体 
 * 内容摘要： 
 * 创建人： huangmingxing
 * 创建日期： 2016年11月16日
 * 版本号： v1.0.0
 * 公  司：重邮汇侧
 * 版权所有： (C)2001-2016     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 
@Entity
@Table(name = "area")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Area{

	// Fields

	private Integer id;
	private Integer code;
	private Integer parentId;
	private String name;
	private Integer level;

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** full constructor */
	public Area(Integer code, Integer parentId, String name, Integer level) {
		this.code = code;
		this.parentId = parentId;
		this.name = name;
		this.level = level;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code")
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Column(name = "parentId")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}