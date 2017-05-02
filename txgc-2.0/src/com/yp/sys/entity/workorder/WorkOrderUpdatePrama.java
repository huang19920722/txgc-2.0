package com.yp.sys.entity.workorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * TCyhcWorkOrderUpdatePrama entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "t_cyhc_work_order_update_prama")
public class WorkOrderUpdatePrama implements java.io.Serializable {

	// Fields

	private Long id;
	private String pramaCode;//参数code
	private String pramaName;//参数名称
	private Long workOrderId;//工单id

	// Constructors




	/** default constructor */
	public WorkOrderUpdatePrama() {
	}

	/** minimal constructor */
	public WorkOrderUpdatePrama(Long id) {
		this.id = id;
	}

	/** full constructor */
	public WorkOrderUpdatePrama(Long id, String pramaCode,
			String pramaName, Long workOrderId) {
		this.id = id;
		this.pramaCode = pramaCode;
		this.pramaName = pramaName;
		this.workOrderId = workOrderId;
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

	@Column(name = "prama_code", length = 50)
	public String getPramaCode() {
		return this.pramaCode;
	}

	public void setPramaCode(String pramaCode) {
		this.pramaCode = pramaCode;
	}

	@Column(name = "prama_name", length = 50)
	public String getPramaName() {
		return this.pramaName;
	}

	public void setPramaName(String pramaName) {
		this.pramaName = pramaName;
	}

	@Column(name="work_order_id")
	public Long getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}

}