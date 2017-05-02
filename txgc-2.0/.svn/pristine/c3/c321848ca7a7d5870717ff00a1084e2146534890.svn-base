package com.yp.sys.entity.workorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCyhcThresholdInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_threshold_info", catalog = "txgc")
public class ThresholdInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String code;
	private Integer thresholdType;
	private String vmin;
	private String vmax;
	private String networkStandard;
	private Integer orgId;
	private Integer wparameterId;

	// Constructors

	/** default constructor */
	public ThresholdInfo() {
	}

	/** minimal constructor */
	public ThresholdInfo(Integer id, String code, Integer wparameterId) {
		this.id = id;
		this.code = code;
		this.wparameterId = wparameterId;
	}

	/** full constructor */
	public ThresholdInfo(Integer id, String name, String code,
			Integer thresholdType, String vmin, String vmax,
			String networkStandard, Integer orgId, Integer wparameterId) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.thresholdType = thresholdType;
		this.vmin = vmin;
		this.vmax = vmax;
		this.networkStandard = networkStandard;
		this.orgId = orgId;
		this.wparameterId = wparameterId;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", nullable = false, length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "threshold_type")
	public Integer getThresholdType() {
		return this.thresholdType;
	}

	public void setThresholdType(Integer thresholdType) {
		this.thresholdType = thresholdType;
	}

	@Column(name = "vmin", length = 10)
	public String getVmin() {
		return this.vmin;
	}

	public void setVmin(String vmin) {
		this.vmin = vmin;
	}

	@Column(name = "vmax", length = 10)
	public String getVmax() {
		return this.vmax;
	}

	public void setVmax(String vmax) {
		this.vmax = vmax;
	}

	@Column(name = "network_standard", length = 2)
	public String getNetworkStandard() {
		return this.networkStandard;
	}

	public void setNetworkStandard(String networkStandard) {
		this.networkStandard = networkStandard;
	}

	@Column(name = "org_id")
	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "wparameter_id", nullable = false)
	public Integer getWparameterId() {
		return this.wparameterId;
	}

	public void setWparameterId(Integer wparameterId) {
		this.wparameterId = wparameterId;
	}

}