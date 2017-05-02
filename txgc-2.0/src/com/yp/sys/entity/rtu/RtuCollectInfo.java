package com.yp.sys.entity.rtu;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * TCyhcRtuCollectInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_rtu_collect_info")
public class RtuCollectInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private RtuConfigInfo config;//配置ID
	private Timestamp uploadTime;//上传时间
	private Integer sid;//sid
	private String content;//json字符串存放位置
	private String slaveState;//寄存器状态
	private RtuBaseInfo rtu;//rtu基础信息

	// Constructors

	/** default constructor */
	public RtuCollectInfo() {
	}

	/** minimal constructor */
	public RtuCollectInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public RtuCollectInfo(Long id, RtuConfigInfo config, Timestamp uploadTime,
			Integer sid, String content, String slaveState) {
		this.id = id;
		this.config = config;
		this.uploadTime = uploadTime;
		this.sid = sid;
		this.content = content;
		this.slaveState = slaveState;
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

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "config_id")
	public RtuConfigInfo getConfig() {
		return config;
	}

	public void setConfig(RtuConfigInfo config) {
		this.config = config;
	}


	@Column(name = "upload_time", length = 19)
	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	@Column(name = "sid")
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "slave_state", length = 10)
	public String getSlaveState() {
		return this.slaveState;
	}

	public void setSlaveState(String slaveState) {
		this.slaveState = slaveState;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "rtu_id")
	public RtuBaseInfo getRtu() {
		return rtu;
	}

	public void setRtu(RtuBaseInfo rtu) {
		this.rtu = rtu;
	}

}