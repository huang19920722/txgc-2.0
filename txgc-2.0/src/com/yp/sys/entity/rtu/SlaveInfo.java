package com.yp.sys.entity.rtu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TCyhcSlaveInfo entity. @author MyEclipse Persistence Tools
 *用于配置与采集属性的中间表（临时slave信息表）
 */
@Entity
@Table(name = "t_cyhc_slave_info")
public class SlaveInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String slaveName;
	private Long configId;
	
	private int slaveOrder;
	private int slaveId;

	// Constructors

	/** default constructor */
	public SlaveInfo() {
	}

	/** minimal constructor */
	public SlaveInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public SlaveInfo(Long id, String slaveName, Long configId) {
		this.id = id;
		this.slaveName = slaveName;
		this.configId = configId;
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

	@Column(name = "slave_name", length = 50)
	public String getSlaveName() {
		return this.slaveName;
	}

	public void setSlaveName(String slaveName) {
		this.slaveName = slaveName;
	}

	@Column(name = "config_id")
	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}
@Column(name="slave_order")
	public int getSlaveOrder() {
		return slaveOrder;
	}

	public void setSlaveOrder(int slaveOrder) {
		this.slaveOrder = slaveOrder;
	}
	@Column(name="slave_id")
	public int getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(int slaveId) {
		this.slaveId = slaveId;
	}
	
	

}