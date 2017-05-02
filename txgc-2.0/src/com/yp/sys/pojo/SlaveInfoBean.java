package com.yp.sys.pojo;

public class SlaveInfoBean {
	
	private Long id;
	private String slaveName;
	private Long configId;
	
	private int slaveOrder;
	private int slaveId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSlaveName() {
		return slaveName;
	}
	public void setSlaveName(String slaveName) {
		this.slaveName = slaveName;
	}
	public Long getConfigId() {
		return configId;
	}
	public void setConfigId(Long configId) {
		this.configId = configId;
	}
	public int getSlaveOrder() {
		return slaveOrder;
	}
	public void setSlaveOrder(int slaveOrder) {
		this.slaveOrder = slaveOrder;
	}
	public int getSlaveId() {
		return slaveId;
	}
	public void setSlaveId(int slaveId) {
		this.slaveId = slaveId;
	}
	
	
}
