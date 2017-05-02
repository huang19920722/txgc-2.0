package com.yp.sys.pojo;

import java.sql.Timestamp;

public class RtuBaseInfoBean {
	private Long id;
	private String rtuAdress;//RTU地址
	private String rtuName;//RTU名称
	private Long cId;//最新配置ID
	private Integer cState;//采集配置状态
	private Timestamp createTime;//创建时间
	private String createUserName;//创建用户ID
	private String modifyUserName;//修改用户Id
	private Timestamp modifyTime;//修改时间
	private int delayTime;//延时
	private int reserveColumn;//预留字段
	
	
	public int getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}
	public int getReserveColumn() {
		return reserveColumn;
	}
	public void setReserveColumn(int reserveColumn) {
		this.reserveColumn = reserveColumn;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getRtuAdress() {
		return rtuAdress;
	}
	public void setRtuAdress(String rtuAdress) {
		this.rtuAdress = rtuAdress;
	}
	public String getRtuName() {
		return rtuName;
	}
	public void setRtuName(String rtuName) {
		this.rtuName = rtuName;
	}

	public Long getcId() {
		return cId;
	}
	public void setcId(Long cId) {
		this.cId = cId;
	}

	public Integer getcState() {
		return cState;
	}
	public void setcState(Integer cState) {
		this.cState = cState;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getModifyUserName() {
		return modifyUserName;
	}
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
}
