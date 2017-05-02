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

import com.yp.sys.entity.User;

/**
 * TCyhcRtuInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_rtu_info")
public class RtuBaseInfo implements java.io.Serializable {

	// Fields

	private Long id;

	private String rtuAdress;//RTU地址
	private String rtuName;//RTU名称
	private Long cId;//最新配置ID
	private Integer cState;//采集配置状态
	
	private int delayTime;//延时
	private int reserveColumn;//预留字段

	private Timestamp createTime;//创建时间
	private User createUser;//创建用户ID
	private User modifyUser;//修改用户Id
	private Timestamp modifyTime;//修改时间

	// Constructors

	/** default constructor */
	public RtuBaseInfo() {
	}

	/** minimal constructor */
	public RtuBaseInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public RtuBaseInfo(Long id,
			Timestamp createTime,
			Timestamp modifyTime) {
		this.id = id;

		this.createTime = createTime;

		this.modifyTime = modifyTime;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	@Column(name = "create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "create_user_id")
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "modify_user_id")
	public User getModifyUser() {
		return modifyUser;
	}



	public void setModifyUser(User modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Column(name = "modify_time", length = 19)
	public Timestamp getModifyTime() {
		return this.modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Column(name = "rtu_adress")
	public String getRtuAdress() {
		return rtuAdress;
	}

	public void setRtuAdress(String rtuAdress) {
		this.rtuAdress = rtuAdress;
	}
	@Column(name = "rtu_name")
	public String getRtuName() {
		return rtuName;
	}

	public void setRtuName(String rtuName) {
		this.rtuName = rtuName;
	}



	@Column(name = "c_state")
	public Integer getcState() {
		return cState;
	}

	public void setcState(Integer cState) {
		this.cState = cState;
	}
	@Column(name = "c_id")
	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}
	
	@Column(name="delay_time")
	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	@Column(name="reserve_column")
	public int getReserveColumn() {
		return reserveColumn;
	}

	public void setReserveColumn(int reserveColumn) {
		this.reserveColumn = reserveColumn;
	}
}