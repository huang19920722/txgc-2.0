package com.yp.sys.entity.terminal;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.yp.sys.entity.organization.Organization;

/**
 * TCyhcTerminalUseInfo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_cyhc_terminal_use_info", catalog = "txgc")
public class TerminalUseInfo  implements java.io.Serializable {

	// Fields
	private Long id;//终端使用情况Id
	private Long terminalId;//终端Id
	private Long terminalUserId;//终端使用者Id
	private String terminalUserName;//终端使用者姓名
	private String terminalCode;//终端编码
	private String terminalUserPhone;//终端使用者电话
	private String terminalUserPhoneC;//终端使用者电话c
	private Timestamp useTime;//使用时间
	private String useAreaName;//使用地区名称
	private String UseAreaCode;//使用地区编码
	private String useAdress;//使用地点
	private String terminalState;//终端状态
	private Timestamp backTime;//归还时间
	private Double point1Longitude;//点一经度
	private Double point1Latitude;//点一纬度
	private Double point2Longitude;//点二经度
	private Double point2Latitude;//点二纬度
	private Double point3Longitude;//点三经度
	private Double point3Latitude;//点三纬度
	private Double point4Longitude;//点四经度
	private Double point4Latitude;//点四纬度
	private Organization org;//运营商
	private String orgIdForSearch;//运营商查询
	private Long orgId;//运营商Id
	private String remarks;//备注
	private Timestamp creatTime;//创建时间
	private String recordStatus;//记录状态
	private Long createUserId;//创建人Id
	private Long modifyUserId;//修改人Id
	private Timestamp modifyTime;//修改时间
	private String useTime1;//使用时间1
	private String backTime1;//归还时间1
	private Date createTime;
	// Constructors

	@Column(name="use_area_name")
	public String getUseAreaName() {
		return useAreaName;
	}

	public void setUseAreaName(String useAreaName) {
		this.useAreaName = useAreaName;
	}
	@Column(name="modifyTime")
	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Column(name="use_area_code")
	public String getUseAreaCode() {
		return UseAreaCode;
	}

	public void setUseAreaCode(String useAreaCode) {
		UseAreaCode = useAreaCode;
	}

	@Column(name="point1_longitude")
	public Double getPoint1Longitude() {
		return point1Longitude;
	}

	public void setPoint1Longitude(Double point1Longitude) {
		this.point1Longitude = point1Longitude;
	}

	@Column(name="point1_latitude")
	public Double getPoint1Latitude() {
		return point1Latitude;
	}

	public void setPoint1Latitude(Double point1Latitude) {
		this.point1Latitude = point1Latitude;
	}

	@Column(name="point2_longitude")
	public Double getPoint2Longitude() {
		return point2Longitude;
	}

	public void setPoint2Longitude(Double point2Longitude) {
		this.point2Longitude = point2Longitude;
	}

	@Column(name="point2_latitude")
	public Double getPoint2Latitude() {
		return point2Latitude;
	}

	public void setPoint2Latitude(Double point2Latitude) {
		this.point2Latitude = point2Latitude;
	}

	@Id
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "com.yp.sys.util.IdGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name="recordStatus")
	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	@Column(name="createUserId")
	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name="modifyUserId")
	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	@Column(name = "terminal_id")
	public Long getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "terminal_user_id")
	public Long getTerminalUserId() {
		return this.terminalUserId;
	}

	public void setTerminalUserId(Long terminalUserId) {
		this.terminalUserId = terminalUserId;
	}

	@Column(name = "terminal_user_name", length = 20)
	public String getTerminalUserName() {
		return this.terminalUserName;
	}

	public void setTerminalUserName(String terminalUserName) {
		this.terminalUserName = terminalUserName;
	}

	@Column(name = "terminal_code", length = 30)
	public String getTerminalCode() {
		return this.terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	@Column(name = "terminal_user_phone", length = 20)
	public String getTerminalUserPhone() {
		return this.terminalUserPhone;
	}

	public void setTerminalUserPhone(String terminalUserPhone) {
		this.terminalUserPhone = terminalUserPhone;
	}

	@Column(name = "terminal_user_phone_c", length = 20)
	public String getTerminalUserPhoneC() {
		return this.terminalUserPhoneC;
	}

	public void setTerminalUserPhoneC(String terminalUserPhoneC) {
		this.terminalUserPhoneC = terminalUserPhoneC;
	}

	@Column(name = "use_time", length = 19)
	public Timestamp getUseTime() {
		return this.useTime;
	}

	public void setUseTime(Timestamp useTime) {
		this.useTime = useTime;
	}
	@Column(name = "use_adress", length = 200)
	public String getUseAdress() {
		return this.useAdress;
	}

	public void setUseAdress(String useAdress) {
		this.useAdress = useAdress;
	}

	@Column(name = "terminal_state", length = 20)
	public String getTerminalState() {
		return this.terminalState;
	}

	public void setTerminalState(String terminalState) {
		this.terminalState = terminalState;
	}

	@Column(name = "back_time", length = 19)
	public Timestamp getBackTime() {
		return this.backTime;
	}

	public void setBackTime(Timestamp backTime) {
		this.backTime = backTime;
	}
	@Column(name = "creat_time", length = 19)
	public Timestamp getCreatTime() {
		return this.creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}


	@Column(name = "remarks", length = 500)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Transient
	public String getUseTime1() {
		return useTime1;
	}

	public void setUseTime1(String useTime1) {
		this.useTime1 = useTime1;
	}
	@Transient
	public String getBackTime1() {
		return backTime1;
	}

	public void setBackTime1(String backTime1) {
		this.backTime1 = backTime1;
	}

	@Transient
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	@Column(name="point3_longitude")
	public Double getPoint3Longitude() {
		return point3Longitude;
	}

	public void setPoint3Longitude(Double point3Longitude) {
		this.point3Longitude = point3Longitude;
	}
	@Column(name="point3_latitude")
	public Double getPoint3Latitude() {
		return point3Latitude;
	}

	public void setPoint3Latitude(Double point3Latitude) {
		this.point3Latitude = point3Latitude;
	}
	@Column(name="point4_longitude")
	public Double getPoint4Longitude() {
		return point4Longitude;
	}
	
	public void setPoint4Longitude(Double point4Longitude) {
		this.point4Longitude = point4Longitude;
	}
	@Column(name="point4_latitude")
	public Double getPoint4Latitude() {
		return point4Latitude;
	}

	public void setPoint4Latitude(Double point4Latitude) {
		this.point4Latitude = point4Latitude;
	}
	@Transient
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}