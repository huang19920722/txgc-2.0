package com.yp.sys.entity.organization;

// default package

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.yp.sys.common.BaseEntity;


/**
 * 
 * 文件名称:机构信息 内容摘要: //简要描述本文件的内容，包括主要模块、函数及其功能的说明 创 建 人: 创建日期: Dec 6, 2013 公 司:
 * 重庆重邮汇侧有限公司 版权所有: 版权所有(C)2001-2004
 * 
 * 修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容 修改日期： 版 本 号： 修 改 人： 修改内容： 修改记录2：…
 * 
 */
@Entity
@Table(name = "ORGANIZATION")
@Where(clause="recordStatus='Y'")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Organization extends BaseEntity {
	/***/
	private static final long serialVersionUID = 4498268582277434287L;
	// Fields

	private Organization parentOrgan;
	private String orgName;
	private String orgCode;
	private String orgDesc;
	private String legalRep;//法定代表人 主责单位
	private String phone;
	private String ownShip;
	private Long regFund;
	private String licenseNo;
	private Long staffNum;
	private String isLeaf;//0 1
	private Long nodeLevel;
	private String status;
	private Long operatorId;
	private Date opTime;
	private String orgIndexId;
	private Long sortNumber;
	private String orgMark;// 机构标识
	private Integer childsCount;//子组织个数 用于判断是否有子节点
	private Long sortNum;
	
	private String periodicals;
	
	private String code;
	
	private String standardcode;
	
	private String areaCode;//机构所属区域code
	
	private String areaName;//机构所属区域name

	/** default constructor */
	public Organization() {
	}

	/** minimal constructor */
	public Organization(Long id, String orgName, String isLeaf, Long nodeLevel) {
		this.id = id;
		this.orgName = orgName;
		this.isLeaf = isLeaf;
		this.nodeLevel = nodeLevel;
	}

	/** full constructor */
	public Organization(Long id, String orgMark, Organization parentOrgan,
			String orgCode, String orgName, String orgDesc, String legalRep,
			String phone, String ownShip, Long regFund, String licenseNo,
			Long staffNum, String isLeaf, Long nodeLevel, String status,
			Long operatorId, Date opTime, String orgIndexId, Long sortNumber) {
		this.id = id;
		this.parentOrgan = parentOrgan;
		this.orgName = orgName;
		this.orgDesc = orgDesc;
		this.legalRep = legalRep;
		this.phone = phone;
		this.ownShip = ownShip;
		this.regFund = regFund;
		this.licenseNo = licenseNo;
		this.staffNum = staffNum;
		this.isLeaf = isLeaf;
		this.nodeLevel = nodeLevel;
		this.status = status;
		this.operatorId = operatorId;
		this.opTime = opTime;
		this.orgIndexId = orgIndexId;
		this.sortNumber = sortNumber;
		this.orgMark = orgMark;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "PARENT_ID")
	public Organization getParentOrgan() {
		return this.parentOrgan;
	}

	public void setParentOrgan(Organization parentOrgan) {
		this.parentOrgan = parentOrgan;
	}

	@Column(name = "ORG_NAME", nullable = false, length = 64)
	public String getOrgName() {
		return this.orgName;
	}
	

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
	@Column(name = "ORG_CODE", nullable = false, length = 64)
	public String getOrgCode() {
		return this.orgCode;
	}
	

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_DESC", length = 1024)
	public String getOrgDesc() {
		return this.orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	@Column(name = "LEGAL_REP", length = 32)
	public String getLegalRep() {
		return this.legalRep;
	}

	public void setLegalRep(String legalRep) {
		this.legalRep = legalRep;
	}

	@Column(name = "PHONE", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "OWNSHIP", length = 32)
	public String getOwnShip() {
		return this.ownShip;
	}

	public void setOwnShip(String ownShip) {
		this.ownShip = ownShip;
	}

	@Column(name = "REG_FUND", precision = 20, scale = 0)
	public Long getRegFund() {
		return this.regFund;
	}

	public void setRegFund(Long regFund) {
		this.regFund = regFund;
	}

	@Column(name = "LICENSE_NO", length = 32)
	public String getLicenseNo() {
		return this.licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	@Column(name = "STAFF_NUM", precision = 20, scale = 0)
	public Long getStaffNum() {
		return this.staffNum;
	}

	public void setStaffNum(Long staffNum) {
		this.staffNum = staffNum;
	}

	@Column(name = "IS_LEAF", nullable = false, length = 1)
	public String getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Column(name = "NODE_LEVEL", nullable = false, precision = 20, scale = 0)
	public Long getNodeLevel() {
		return this.nodeLevel;
	}

	public void setNodeLevel(Long nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	@Column(name = "STATUS", length = 3)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "OPERATOR_ID", precision = 20, scale = 0)
	public Long getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OP_TIME", length = 7)
	public Date getOpTime() {
		return this.opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	@Column(name = "ORG_INDEX_ID", length = 32)
	public String getOrgIndexId() {
		return this.orgIndexId;
	}

	public void setOrgIndexId(String orgIndexId) {
		this.orgIndexId = orgIndexId;
	}

	@Column(name = "SORT_NUMBER", precision = 20, scale = 0)
	public Long getSortNumber() {
		return this.sortNumber;
	}

	public void setSortNumber(Long sortNumber) {
		this.sortNumber = sortNumber;
	}

	@Column(name = "CODE_MARK", length = 32)
	public String getOrgMark() {
		return orgMark;
	}

	public void setOrgMark(String orgMark) {
		this.orgMark = orgMark;
	}
	
	//t.node_level<1000100010001 and
	@Formula("(select count(*) from organization t where  t.PARENT_ID =id and  t.recordStatus='y')")
	public Integer getChildsCount() {
		return childsCount;
	}

	public void setChildsCount(Integer childsCount) {
		this.childsCount = childsCount;
	}

	public Long getSortNum() {
		return sortNum;
	}

	public void setSortNum(Long sortNum) {
		this.sortNum = sortNum;
	}
	
	@Column(name="periodicals")
	public String getPeriodicals() {
		return periodicals;
	}
	public void setPeriodicals(String periodicals) {
		this.periodicals = periodicals;
	}
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="standardcode")
	public String getStandardcode() {
		return standardcode;
	}
	public void setStandardcode(String standardcode) {
		this.standardcode = standardcode;
	}
	@Column(name = "area_code")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	@Column(name = "area_name")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}