package com.yp.sys.entity.workorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCyhcGb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_gb", catalog = "txgc")
public class Gb implements java.io.Serializable {

	// Fields

	private Integer id;
	private String gbCode;
	private String gbName;
	private String gbType;
	private String gbOrder;
	private String gbDescribe;

	// Constructors

	/** default constructor */
	public Gb() {
	}

	/** minimal constructor */
	public Gb(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Gb(Integer id, String gbCode, String gbName, String gbType,
			String gbOrder, String gbDescribe) {
		this.id = id;
		this.gbCode = gbCode;
		this.gbName = gbName;
		this.gbType = gbType;
		this.gbOrder = gbOrder;
		this.gbDescribe = gbDescribe;
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

	@Column(name = "gb_code", length = 50)
	public String getGbCode() {
		return this.gbCode;
	}

	public void setGbCode(String gbCode) {
		this.gbCode = gbCode;
	}

	@Column(name = "gb_name", length = 50)
	public String getGbName() {
		return this.gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	@Column(name = "gb_type", length = 50)
	public String getGbType() {
		return this.gbType;
	}

	public void setGbType(String gbType) {
		this.gbType = gbType;
	}

	@Column(name = "gb_order", length = 50)
	public String getGbOrder() {
		return this.gbOrder;
	}

	public void setGbOrder(String gbOrder) {
		this.gbOrder = gbOrder;
	}

	@Column(name = "gb_describe", length = 50)
	public String getGbDescribe() {
		return this.gbDescribe;
	}

	public void setGbDescribe(String gbDescribe) {
		this.gbDescribe = gbDescribe;
	}

}