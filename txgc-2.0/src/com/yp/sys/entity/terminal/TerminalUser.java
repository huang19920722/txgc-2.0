package com.yp.sys.entity.terminal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.yp.sys.common.BaseEntity;
import com.yp.sys.entity.organization.Organization;

/**   
 * 文件名称：终端使用者实体 
 * 创建人： nihui
 * 创建日期： 2016年11月116日
 * 版本号： v1.0.0
 * 公  司：重邮汇测
 * 版权所有： (C)2001-2015     
 * 修改记录
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/
@Entity
@Table(name="t_cyhc_terminal_user")
@Where(clause="recordStatus='Y'")
public class TerminalUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7807015782884561957L;
	
	private String IDCard;//身份证号
	private String name;//姓名
	private String phone;//电话
	private String password;//密码
	private Organization org;//运营商
	private String orgIdForSearch;//运营商查询
	private Long orgId;//运营商Id
	@Column(length=18)
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=11)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(length=6)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name ="org_id")
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	@Transient
	public String getOrgIdForSearch() {
		return orgIdForSearch;
	}
	public void setOrgIdForSearch(String orgIdForSearch) {
		this.orgIdForSearch = orgIdForSearch;
	}
	@Transient
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	

	
}
