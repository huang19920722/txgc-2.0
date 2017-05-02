package com.yp.sys.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.yp.sys.common.BaseEntity;
import com.yp.sys.entity.organization.Organization;

/**
 * 
 * 
 * 文件名称: 角色实体
 * 内容摘要: //简要描述本文件的内容，包括主要模块、函数及其功能的说明
 * 创 建 人:mominglong
 * 创建日期: 2013-2-3
 * 公    司: 重庆重邮汇侧有限公司
 * 版权所有: 版权所有(C)2001-2004
 * 
 * 修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容
 *   修改日期： 2015/5/20
 *   版 本 号：
 *   修 改 人： lanxiaowei
 *   修改内容： 将xml全部移除，取消上下级角色关系，用注解方式实现角色菜单，角色用户关联关系管理
 * 修改记录2：…
 *
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity {

	/***/
	private static final long serialVersionUID = 3761288219059616551L;
	// Fields

	
	private String text;
	private BigDecimal seq;
	private String descript;
	private List<Menu> menus;//角色拥有菜单
	//private List<User> users;//角色拥有用户
	private Organization organization;//角色所属组织
	
	private Organization createOrg;//创建机构
	private String systemRole;//是否是内置角色
	// Constructors

	/** default constructor */
	public Role() {
	}
	// Property accessors
	@Column(length=100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getSeq() {
		return this.seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}
	@Column(length=100)
	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_to_menu", joinColumns = {@JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "symenu_id") })
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	/*@ManyToMany(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinTable(name = "sys_user_to_role", joinColumns = {@JoinColumn(name = "SYROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "SYUSER_ID") })
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}*/
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="organizationId")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="create_org_id")
	public Organization getCreateOrg() {
		return createOrg;
	}
	public void setCreateOrg(Organization createOrg) {
		this.createOrg = createOrg;
	}
	@Column(name="SYSTEM_ROLE")
	public String getSystemRole() {
		return systemRole;
	}
	public void setSystemRole(String systemRole) {
		this.systemRole = systemRole;
	}
	
	
}