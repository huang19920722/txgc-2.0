package com.yp.sys.pojo;

import java.sql.Timestamp;

/**
 * 
 * @author huangmingxing
 *用于接收创建工单的参数
 */
public class CreatWorkOrderBean {
	private String networkType;//网络制式
	private String cname;//小区名字
	private Long wparameterId;// 标准公参ID
	private String orgName;//机构名称
	private Long orgId;//机构ID
	private String terminalCode;//终端编码
	private Long terminalId;//终端id
	private String terminalUser;//终端当前使用的人员
	private Timestamp timeLimit;//工单预计完成时间
	private String remark;//工单下发备注
	
	private String paraObjsText;//采集项
	
	
	
	public String getParaObjsText() {
		return paraObjsText;
	}
	public void setParaObjsText(String paraObjsText) {
		this.paraObjsText = paraObjsText;
	}
	
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Long getWparameterId() {
		return wparameterId;
	}
	public void setWparameterId(Long wparameterId) {
		this.wparameterId = wparameterId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public Long getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}
	public String getTerminalUser() {
		return terminalUser;
	}
	public void setTerminalUser(String terminalUser) {
		this.terminalUser = terminalUser;
	}
	public Timestamp getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Timestamp timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	
	
	
}
