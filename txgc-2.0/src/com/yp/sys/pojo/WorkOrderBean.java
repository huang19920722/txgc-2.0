package com.yp.sys.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 工单信息
 */
public class WorkOrderBean implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer terminalId;
	private Integer workerParameterId;
	private Integer userId;
	private String workOrderNum;
	private String orderState;
	private Timestamp completeTime;
	private Integer timeLimit;
	private String issuedWay;
	private String issuedRemark;
	private String explainState;
	private Timestamp explainTime;
	private String explainRemark;
	private Timestamp createTime;
	private Integer orgId;
	private String excFlag;

	// Property accessors
	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}

	
	public Integer getWorkerParameterId() {
		return this.workerParameterId;
	}

	public void setWorkerParameterId(Integer workerParameterId) {
		this.workerParameterId = workerParameterId;
	}

	
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public String getWorkOrderNum() {
		return this.workOrderNum;
	}

	public void setWorkOrderNum(String workOrderNum) {
		this.workOrderNum = workOrderNum;
	}

	
	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	
	public Timestamp getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(Timestamp completeTime) {
		this.completeTime = completeTime;
	}

	
	public Integer getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	
	public String getIssuedWay() {
		return this.issuedWay;
	}

	public void setIssuedWay(String issuedWay) {
		this.issuedWay = issuedWay;
	}

	
	public String getIssuedRemark() {
		return this.issuedRemark;
	}

	public void setIssuedRemark(String issuedRemark) {
		this.issuedRemark = issuedRemark;
	}

	
	public String getExplainState() {
		return this.explainState;
	}

	public void setExplainState(String explainState) {
		this.explainState = explainState;
	}

	
	public Timestamp getExplainTime() {
		return this.explainTime;
	}

	public void setExplainTime(Timestamp explainTime) {
		this.explainTime = explainTime;
	}

	
	public String getExplainRemark() {
		return this.explainRemark;
	}

	public void setExplainRemark(String explainRemark) {
		this.explainRemark = explainRemark;
	}

	
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	
	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	
	public String getExcFlag() {
		return this.excFlag;
	}

	public void setExcFlag(String excFlag) {
		this.excFlag = excFlag;
	}

}