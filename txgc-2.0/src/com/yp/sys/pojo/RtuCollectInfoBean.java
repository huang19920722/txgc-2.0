package com.yp.sys.pojo;

import java.sql.Timestamp;

import com.yp.sys.entity.rtu.RtuBaseInfo;

public class RtuCollectInfoBean {
	private Long id;
	//private RtuConfigInfo config;//配置ID
	private Long rtuConfigId;//配置ID
	private Timestamp uploadTime;//上传时间
	private Integer sid;//sid
	private String content;//json字符串存放位置
	private String slaveState;//寄存器状态
	//private RtuBaseInfo rtu;//rtu基础信息
	private Long rtuId;//rtuID
	
	private String rtuName;//模组名称
	private String rtuAdress;//模组名称
	
	private String uploadTimeStart;//上传区间开始时间
	private String uploadTimeEnd;//上传区间结束时间
	
	
	public String getRtuAdress() {
		return rtuAdress;
	}
	public void setRtuAdress(String rtuAdress) {
		this.rtuAdress = rtuAdress;
	}

	
	
	public Long getRtuConfigId() {
		return rtuConfigId;
	}
	public void setRtuConfigId(Long rtuConfigId) {
		this.rtuConfigId = rtuConfigId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSlaveState() {
		return slaveState;
	}
	public void setSlaveState(String slaveState) {
		this.slaveState = slaveState;
	}
	public Long getRtuId() {
		return rtuId;
	}
	public void setRtuId(Long rtuId) {
		this.rtuId = rtuId;
	}

	
	public String getUploadTimeStart() {
		return uploadTimeStart;
	}
	public void setUploadTimeStart(String uploadTimeStart) {
		this.uploadTimeStart = uploadTimeStart;
	}
	public String getUploadTimeEnd() {
		return uploadTimeEnd;
	}
	public void setUploadTimeEnd(String uploadTimeEnd) {
		this.uploadTimeEnd = uploadTimeEnd;
	}
	public String getRtuName() {
		return rtuName;
	}
	public void setRtuName(String rtuName) {
		this.rtuName = rtuName;
	}
	
	
}
