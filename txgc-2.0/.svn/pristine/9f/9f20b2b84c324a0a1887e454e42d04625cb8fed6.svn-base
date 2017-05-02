package com.yp.sys.entity.workorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TCyhcCommunityInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_community_info")
public class CommunityInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Long wparameterId;//工参ID
	private String lac;
	private String cname;//小区名称
	private String detailAddress;
	private String provinceName;
	private String countyName;
	private String streetName;
	private String provinceCode;
	private String countyCode;
	private String streetCode;
	private String networkStandard;//网络制式
	private String ci;//CI
	private String recordStatus;//记录状态

	// Constructors

	/** default constructor */
	public CommunityInfo() {
	}

	/** minimal constructor */
	public CommunityInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CommunityInfo(Long id, Long wparameterId, String lac,
			String cname, String detailAddress, String provinceName,
			String countyName, String streetName, String provinceCode,
			String countyCode, String streetCode, String networkStandard,
			String ci) {
		this.id = id;
		this.wparameterId = wparameterId;
		this.lac = lac;
		this.cname = cname;
		this.detailAddress = detailAddress;
		this.provinceName = provinceName;
		this.countyName = countyName;
		this.streetName = streetName;
		this.provinceCode = provinceCode;
		this.countyCode = countyCode;
		this.streetCode = streetCode;
		this.networkStandard = networkStandard;
		this.ci = ci;
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

	@Column(name = "wparameter_id")
	public Long getWparameterId() {
		return this.wparameterId;
	}

	public void setWparameterId(Long wparameterId) {
		this.wparameterId = wparameterId;
	}

	@Column(name = "LAC", length = 50)
	public String getLac() {
		return this.lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	@Column(name = "cname", length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "detail_address", length = 100)
	public String getDetailAddress() {
		return this.detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	@Column(name = "province_name", length = 100)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "county_name", length = 100)
	public String getCountyName() {
		return this.countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	@Column(name = "street_name", length = 100)
	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name = "province_code", length = 100)
	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Column(name = "county_code", length = 100)
	public String getCountyCode() {
		return this.countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	@Column(name = "street_code", length = 100)
	public String getStreetCode() {
		return this.streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}

	@Column(name = "network_standard", length = 2)
	public String getNetworkStandard() {
		return this.networkStandard;
	}

	public void setNetworkStandard(String networkStandard) {
		this.networkStandard = networkStandard;
	}

	@Column(name = "CI", length = 50)
	public String getCi() {
		return this.ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}
	@Column(name="recordStatus")
	public String getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}

	
}