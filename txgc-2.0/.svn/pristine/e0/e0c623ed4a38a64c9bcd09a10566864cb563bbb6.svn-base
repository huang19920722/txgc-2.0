package com.yp.sys.entity.workorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TCyhcWorkOrderParameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_work_order_parameter")
public class WorkOrderParameter implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String code;
	private Long workOrderId;
	private Integer orderNum;

	// Constructors

	/** default constructor */
	public WorkOrderParameter() {
	}

	/** minimal constructor */
	public WorkOrderParameter(Long id) {
		this.id = id;
	}

	/** full constructor */
	public WorkOrderParameter(Long id, String name, String code,
			Long workOrderId, Integer orderNum) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.workOrderId = workOrderId;
		this.orderNum = orderNum;
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

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "work_order_id")
	public Long getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}

	@Column(name = "orderNum")
	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

}