package com.yp.sys.entity.rtu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * TCyhcRtuRefer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_rtu_refer")
public class RtuRefer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9162203139956143130L;
	// Fields

	private Long id;
	private String referKey;
	private String referDesc;
	private Long propertyId;
	private Integer referOrder;
	private Long flag;//判断该值是否选中

	// Constructors

	/** default constructor */
	public RtuRefer() {
	}

	/** minimal constructor */
	public RtuRefer(Long id) {
		this.id = id;
	}

	/** full constructor */
	public RtuRefer(Long id, String referKey, String referDesc,
			Long propertyId, Integer referOrder) {
		this.id = id;
		this.referKey = referKey;
		this.referDesc = referDesc;
		this.propertyId = propertyId;
		this.referOrder = referOrder;
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

	@Column(name = "refer_key", length = 50)
	public String getReferKey() {
		return this.referKey;
	}

	public void setReferKey(String referKey) {
		this.referKey = referKey;
	}

	@Column(name = "refer_desc", length = 100)
	public String getReferDesc() {
		return this.referDesc;
	}

	public void setReferDesc(String referDesc) {
		this.referDesc = referDesc;
	}

	@Column(name = "property_id")
	public Long getPropertyId() {
		return this.propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	@Column(name = "refer_order")
	public Integer getReferOrder() {
		return this.referOrder;
	}

	public void setReferOrder(Integer referOrder) {
		this.referOrder = referOrder;
	}
	@Transient
	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
	
}