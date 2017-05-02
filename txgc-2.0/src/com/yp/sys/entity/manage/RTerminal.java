package com.yp.sys.entity.manage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * RTerminal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "r_terminal")

public class RTerminal implements java.io.Serializable {
 
	
	private static final long serialVersionUID = -8159597338037048114L;
	// Fields

	private Long id;
	private String number;//终端编号
	private String remark;//备注

	// Constructors

	/** default constructor */
	public RTerminal() {
	}

	/** minimal constructor */
	public RTerminal(Long id) {
		this.id = id;
	}

	/** full constructor */
	public RTerminal(Long id, String number, String remark) {
		this.id = id;
		this.number = number;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "com.yp.sys.util.IdGenerator")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "_number", length = 20)

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "remark", length = 200)

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}