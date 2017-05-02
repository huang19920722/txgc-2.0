package com.yp.sys.entity.workorder;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCyhcWorkParameterLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_work_parameter_log", catalog = "txgc")
public class WorkParameterLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String workParameterNumber;
	private String operType;
	private String operContent;
	private Timestamp operTime;
	private Integer operId;
	private Integer orgId;

	// Constructors

	/** default constructor */
	public WorkParameterLog() {
	}

	/** minimal constructor */
	public WorkParameterLog(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WorkParameterLog(Integer id, String workParameterNumber,
			String operType, String operContent, Timestamp operTime,
			Integer operId, Integer orgId) {
		this.id = id;
		this.workParameterNumber = workParameterNumber;
		this.operType = operType;
		this.operContent = operContent;
		this.operTime = operTime;
		this.operId = operId;
		this.orgId = orgId;
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

	@Column(name = "work_parameter_number", length = 50)
	public String getWorkParameterNumber() {
		return this.workParameterNumber;
	}

	public void setWorkParameterNumber(String workParameterNumber) {
		this.workParameterNumber = workParameterNumber;
	}

	@Column(name = "oper_type", length = 10)
	public String getOperType() {
		return this.operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	@Column(name = "oper_content", length = 65535)
	public String getOperContent() {
		return this.operContent;
	}

	public void setOperContent(String operContent) {
		this.operContent = operContent;
	}

	@Column(name = "oper_time", length = 19)
	public Timestamp getOperTime() {
		return this.operTime;
	}

	public void setOperTime(Timestamp operTime) {
		this.operTime = operTime;
	}

	@Column(name = "oper_id")
	public Integer getOperId() {
		return this.operId;
	}

	public void setOperId(Integer operId) {
		this.operId = operId;
	}

	@Column(name = "org_id")
	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

}