package com.yp.sys.entity.terminal;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.yp.sys.common.BaseEntity;
import com.yp.sys.entity.organization.Organization;

/**
 * TCyhcTerminalInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_terminal_info", catalog = "txgc")
public class TerminalInfo extends BaseEntity implements java.io.Serializable {

	// Fields
	private String code;//终端编码
	private String model;//终端型号
	private String terminalState;//终端状态
	private Timestamp castTime;//投用时间
	private Organization org;//运营商
	private Long orgId;//运营商Id
	private String castTime1;//投用时间1
	private TerminalUser terminalUser;//终端使用者信息
	private String orgIdForSearch;//运营商查询
	// Constructors

	

	/** default constructor */
	public TerminalInfo() {
	}
	@Transient
	public String getCastTime1() {
		return castTime1;
	}
	public void setCastTime1(String castTime1) {
		this.castTime1 = castTime1;
	}
	// Property accessors
	@Column(name = "code", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "model", length = 50)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "terminal_state", length = 20)
	public String getTerminalState() {
		return this.terminalState;
	}

	public void setTerminalState(String terminalState) {
		this.terminalState = terminalState;
	}

	@Column(name = "cast_time", length = 19)
	public Timestamp getCastTime() {
		return this.castTime;
	}

	public void setCastTime(Timestamp castTime) {
		this.castTime = castTime;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name ="operator_id")
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name ="terminal_user_id")
	public TerminalUser getTerminalUser() {
		return terminalUser;
	}
	public void setTerminalUser(TerminalUser terminalUser) {
		this.terminalUser = terminalUser;
	}
	
	@Transient
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	@Transient
	public String getOrgIdForSearch() {
		return orgIdForSearch;
	}
	public void setOrgIdForSearch(String orgIdForSearch) {
		this.orgIdForSearch = orgIdForSearch;
	}
	
}