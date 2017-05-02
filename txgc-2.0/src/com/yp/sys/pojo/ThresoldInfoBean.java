package com.yp.sys.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.yp.sys.common.BaseEntity;


/**   
 * 版权所有：2016-重庆重邮汇测通讯技术有限公司
 * 项目名称：txgc   
 *
 * 类描述：小区阀值Bean对象
 * 类名称：com.yp.sys.pojo.ThresoldInfoBean     
 * 创建人：zhongyang
 * 创建时间：2016-11-17 下午2:48:20   
 * 修改人：
 * 修改时间：2016-11-17 下午2:48:20   
 * 修改备注：   
 * @version   V1.0    
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class ThresoldInfoBean implements java.io.Serializable {
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
	private String xqgc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNetworkStandard() {
		return networkStandard;
	}
	public void setNetworkStandard(String networkStandard) {
		this.networkStandard = networkStandard;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Double getLongitudeMin() {
		return longitudeMin;
	}
	public void setLongitudeMin(Double longitudeMin) {
		this.longitudeMin = longitudeMin;
	}
	public Double getLongitudeMax() {
		return longitudeMax;
	}
	public void setLongitudeMax(Double longitudeMax) {
		this.longitudeMax = longitudeMax;
	}
	public Double getLatitudeMin() {
		return latitudeMin;
	}
	public void setLatitudeMin(Double latitudeMin) {
		this.latitudeMin = latitudeMin;
	}
	public Double getLatitudeMax() {
		return latitudeMax;
	}
	public void setLatitudeMax(Double latitudeMax) {
		this.latitudeMax = latitudeMax;
	}
	public String getSpectrum() {
		return spectrum;
	}
	public void setSpectrum(String spectrum) {
		this.spectrum = spectrum;
	}
	public Double getElectronicUnderAngleMin() {
		return electronicUnderAngleMin;
	}
	public void setElectronicUnderAngleMin(Double electronicUnderAngleMin) {
		this.electronicUnderAngleMin = electronicUnderAngleMin;
	}
	public Double getElectronicUnderAngleMax() {
		return electronicUnderAngleMax;
	}
	public void setElectronicUnderAngleMax(Double electronicUnderAngleMax) {
		this.electronicUnderAngleMax = electronicUnderAngleMax;
	}
	public Double getOrientationAngleMin() {
		return orientationAngleMin;
	}
	public void setOrientationAngleMin(Double orientationAngleMin) {
		this.orientationAngleMin = orientationAngleMin;
	}
	public Double getOrientationAngleMax() {
		return orientationAngleMax;
	}
	public void setOrientationAngleMax(Double orientationAngleMax) {
		this.orientationAngleMax = orientationAngleMax;
	}
	public Double getAntennaHangHighMin() {
		return antennaHangHighMin;
	}
	public void setAntennaHangHighMin(Double antennaHangHighMin) {
		this.antennaHangHighMin = antennaHangHighMin;
	}
	public Double getAntennaHangHighMax() {
		return antennaHangHighMax;
	}
	public void setAntennaHangHighMax(Double antennaHangHighMax) {
		this.antennaHangHighMax = antennaHangHighMax;
	}
	public Double getAltitudeMin() {
		return altitudeMin;
	}
	public void setAltitudeMin(Double altitudeMin) {
		this.altitudeMin = altitudeMin;
	}
	public Double getAltitudeMax() {
		return altitudeMax;
	}
	public void setAltitudeMax(Double altitudeMax) {
		this.altitudeMax = altitudeMax;
	}
	public String getRecodeStatus() {
		return recodeStatus;
	}
	public void setRecodeStatus(String recodeStatus) {
		this.recodeStatus = recodeStatus;
	}
	public Double getMechanicalUnderAngleMin() {
		return mechanicalUnderAngleMin;
	}
	public void setMechanicalUnderAngleMin(Double mechanicalUnderAngleMin) {
		this.mechanicalUnderAngleMin = mechanicalUnderAngleMin;
	}
	public Double getMechanicalUnderAngleMax() {
		return mechanicalUnderAngleMax;
	}
	public void setMechanicalUnderAngleMax(Double mechanicalUnderAngleMax) {
		this.mechanicalUnderAngleMax = mechanicalUnderAngleMax;
	}
	public String getXqgc() {
		return xqgc;
	}
	public void setXqgc(String xqgc) {
		this.xqgc = xqgc;
	}
	
}
