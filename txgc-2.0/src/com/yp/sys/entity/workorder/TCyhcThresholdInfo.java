package com.yp.sys.entity.workorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yp.sys.common.BaseEntity;

/**
 * 
 * 版权所有：2016-重庆重邮汇测通讯技术有限公司
 * 项目名称：txgc   
 *
 * 类描述：小区阀值实体类
 * 类名称：com.yp.sys.entity.params.Params     
 * 创建人：zhongyang
 * 创建时间：2016-11-16 下午3:12:52   
 * 修改人：
 * 修改时间：2016-11-16 下午3:12:52   
 * 修改备注：   
 * @version   V1.0
 */
@Entity
@Table(name = "t_cyhc_threshold_info")
public class TCyhcThresholdInfo implements java.io.Serializable {

	// Fields

	/**
	 */ 
	private static final long serialVersionUID = 1L;
	private Long id;
	private String networkStandard;
	private Long orgId;
	private Double longitudeMin;
	private Double longitudeMax;
	private Double latitudeMin;
	private Double latitudeMax;
	private String spectrum;
	private Double electronicUnderAngleMin;
	private Double electronicUnderAngleMax;
	private Double orientationAngleMin;
	private Double orientationAngleMax;
	private Double antennaHangHighMin;
	private Double antennaHangHighMax;
	private Double altitudeMin;
	private Double altitudeMax;
	private String recodeStatus;
	private Double mechanicalUnderAngleMin;
	private Double mechanicalUnderAngleMax;

	// Constructors

	/** default constructor */
	public TCyhcThresholdInfo() {
	}

	/** minimal constructor */
	public TCyhcThresholdInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TCyhcThresholdInfo(Long id, String networkStandard, Long orgId,
			Double longitudeMin, Double longitudeMax, Double latitudeMin,
			Double latitudeMax, String spectrum,
			Double electronicUnderAngleMin, Double electronicUnderAngleMax,
			Double orientationAngleMin, Double orientationAngleMax,
			Double antennaHangHighMin, Double antennaHangHighMax,
			Double altitudeMin, Double altitudeMax, String recodeStatus,
			Double mechanicalUnderAngleMin, Double mechanicalUnderAngleMax) {
		this.id = id;
		this.networkStandard = networkStandard;
		this.orgId = orgId;
		this.longitudeMin = longitudeMin;
		this.longitudeMax = longitudeMax;
		this.latitudeMin = latitudeMin;
		this.latitudeMax = latitudeMax;
		this.spectrum = spectrum;
		this.electronicUnderAngleMin = electronicUnderAngleMin;
		this.electronicUnderAngleMax = electronicUnderAngleMax;
		this.orientationAngleMin = orientationAngleMin;
		this.orientationAngleMax = orientationAngleMax;
		this.antennaHangHighMin = antennaHangHighMin;
		this.antennaHangHighMax = antennaHangHighMax;
		this.altitudeMin = altitudeMin;
		this.altitudeMax = altitudeMax;
		this.recodeStatus = recodeStatus;
		this.mechanicalUnderAngleMin = mechanicalUnderAngleMin;
		this.mechanicalUnderAngleMax = mechanicalUnderAngleMax;
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

	@Column(name = "network_standard", length = 2)
	public String getNetworkStandard() {
		return this.networkStandard;
	}

	public void setNetworkStandard(String networkStandard) {
		this.networkStandard = networkStandard;
	}

	@Column(name = "org_id")
	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Column(name = "longitude_min", precision = 9, scale = 5)
	public Double getLongitudeMin() {
		return this.longitudeMin;
	}

	public void setLongitudeMin(Double longitudeMin) {
		this.longitudeMin = longitudeMin;
	}

	@Column(name = "longitude_max", precision = 9, scale = 5)
	public Double getLongitudeMax() {
		return this.longitudeMax;
	}

	public void setLongitudeMax(Double longitudeMax) {
		this.longitudeMax = longitudeMax;
	}

	@Column(name = "latitude_min", precision = 9, scale = 5)
	public Double getLatitudeMin() {
		return this.latitudeMin;
	}

	public void setLatitudeMin(Double latitudeMin) {
		this.latitudeMin = latitudeMin;
	}

	@Column(name = "latitude_max", precision = 9, scale = 5)
	public Double getLatitudeMax() {
		return this.latitudeMax;
	}

	public void setLatitudeMax(Double latitudeMax) {
		this.latitudeMax = latitudeMax;
	}

	@Column(name = "spectrum", length = 50)
	public String getSpectrum() {
		return this.spectrum;
	}

	public void setSpectrum(String spectrum) {
		this.spectrum = spectrum;
	}

	@Column(name = "electronic_under_angle_min", precision = 5, scale = 1)
	public Double getElectronicUnderAngleMin() {
		return this.electronicUnderAngleMin;
	}

	public void setElectronicUnderAngleMin(Double electronicUnderAngleMin) {
		this.electronicUnderAngleMin = electronicUnderAngleMin;
	}

	@Column(name = "electronic_under_angle_max", precision = 5, scale = 1)
	public Double getElectronicUnderAngleMax() {
		return this.electronicUnderAngleMax;
	}

	public void setElectronicUnderAngleMax(Double electronicUnderAngleMax) {
		this.electronicUnderAngleMax = electronicUnderAngleMax;
	}

	@Column(name = "orientation_angle_min", precision = 5, scale = 1)
	public Double getOrientationAngleMin() {
		return this.orientationAngleMin;
	}

	public void setOrientationAngleMin(Double orientationAngleMin) {
		this.orientationAngleMin = orientationAngleMin;
	}

	@Column(name = "orientation_angle_max", precision = 5, scale = 1)
	public Double getOrientationAngleMax() {
		return this.orientationAngleMax;
	}

	public void setOrientationAngleMax(Double orientationAngleMax) {
		this.orientationAngleMax = orientationAngleMax;
	}

	@Column(name = "antenna_hang_high_min", precision = 8)
	public Double getAntennaHangHighMin() {
		return this.antennaHangHighMin;
	}

	public void setAntennaHangHighMin(Double antennaHangHighMin) {
		this.antennaHangHighMin = antennaHangHighMin;
	}

	@Column(name = "antenna_hang_high_max", precision = 8)
	public Double getAntennaHangHighMax() {
		return this.antennaHangHighMax;
	}

	public void setAntennaHangHighMax(Double antennaHangHighMax) {
		this.antennaHangHighMax = antennaHangHighMax;
	}

	@Column(name = "altitude_min", precision = 8)
	public Double getAltitudeMin() {
		return this.altitudeMin;
	}

	public void setAltitudeMin(Double altitudeMin) {
		this.altitudeMin = altitudeMin;
	}

	@Column(name = "altitude_max", precision = 8)
	public Double getAltitudeMax() {
		return this.altitudeMax;
	}

	public void setAltitudeMax(Double altitudeMax) {
		this.altitudeMax = altitudeMax;
	}

	@Column(name = "recode_status", length = 2)
	public String getRecodeStatus() {
		return this.recodeStatus;
	}

	public void setRecodeStatus(String recodeStatus) {
		this.recodeStatus = recodeStatus;
	}

	@Column(name = "mechanical_under_angle_min", precision = 8)
	public Double getMechanicalUnderAngleMin() {
		return this.mechanicalUnderAngleMin;
	}

	public void setMechanicalUnderAngleMin(Double mechanicalUnderAngleMin) {
		this.mechanicalUnderAngleMin = mechanicalUnderAngleMin;
	}

	@Column(name = "mechanical_under_angle_max", precision = 8)
	public Double getMechanicalUnderAngleMax() {
		return this.mechanicalUnderAngleMax;
	}

	public void setMechanicalUnderAngleMax(Double mechanicalUnderAngleMax) {
		this.mechanicalUnderAngleMax = mechanicalUnderAngleMax;
	}

}