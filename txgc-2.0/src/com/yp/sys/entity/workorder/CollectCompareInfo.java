package com.yp.sys.entity.workorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * CollectCompareInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_collect_compare_info")
public class CollectCompareInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Double longitude;//经度
	private String isExceptionLongitude;//经度是否异常
	private Double latitude;//纬度
	private String isExceptionLatitude;//纬度是否异常
	private String spectrum;//频段
	private String isExceptionSpectrum;//频段是否异常
	private Double electronicUnderAngle;//电子下倾角
	private String isExceptionUnderAngle;//电子下倾角是否异常
	private String orientationAngle;//方位角
	private String isExceptionOrientationAngle;//方位角是否异常
	private String antennaHangHigh;//天线挂高
	private String isExceptionHangHigh;//天线挂高是否常
	private String altitude;//海拔高度
	private String isExceptionAltitude;//海拔高度是否异常

	// Constructors

	/** default constructor */
	public CollectCompareInfo() {
	}

	/** minimal constructor */
	public CollectCompareInfo(Long collectInfoId) {
		this.id = collectInfoId;
	}

	/** full constructor */
	public CollectCompareInfo(Long collectInfoId, Double longitude,
			String isExceptionLongitude, Double latitude,
			String isExceptionLatitude, String spectrum,
			String isExceptionSpectrum, Double electronicUnderAngle,
			String isExceptionUnderAngle, String orientationAngle,
			String isExceptionOrientationAngle, String antennaHangHigh,
			String isExceptionHangHigh, String altitude,
			String isExceptionAltitude) {
		this.id = collectInfoId;
		this.longitude = longitude;
		this.isExceptionLongitude = isExceptionLongitude;
		this.latitude = latitude;
		this.isExceptionLatitude = isExceptionLatitude;
		this.spectrum = spectrum;
		this.isExceptionSpectrum = isExceptionSpectrum;
		this.electronicUnderAngle = electronicUnderAngle;
		this.isExceptionUnderAngle = isExceptionUnderAngle;
		this.orientationAngle = orientationAngle;
		this.isExceptionOrientationAngle = isExceptionOrientationAngle;
		this.antennaHangHigh = antennaHangHigh;
		this.isExceptionHangHigh = isExceptionHangHigh;
		this.altitude = altitude;
		this.isExceptionAltitude = isExceptionAltitude;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "com.yp.sys.util.IdGenerator")
	public Long getId() {
		return this.id;
	}

	public void setId(Long collectInfoId) {
		this.id = collectInfoId;
	}

	@Column(name = "longitude", precision = 9, scale = 5)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "is_exception_longitude", length = 2)
	public String getIsExceptionLongitude() {
		return this.isExceptionLongitude;
	}

	public void setIsExceptionLongitude(String isExceptionLongitude) {
		this.isExceptionLongitude = isExceptionLongitude;
	}

	@Column(name = "latitude", precision = 9, scale = 5)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "is_exception_latitude", length = 2)
	public String getIsExceptionLatitude() {
		return this.isExceptionLatitude;
	}

	public void setIsExceptionLatitude(String isExceptionLatitude) {
		this.isExceptionLatitude = isExceptionLatitude;
	}

	@Column(name = "spectrum", length = 50)
	public String getSpectrum() {
		return this.spectrum;
	}

	public void setSpectrum(String spectrum) {
		this.spectrum = spectrum;
	}

	@Column(name = "is_exception_spectrum", length = 2)
	public String getIsExceptionSpectrum() {
		return this.isExceptionSpectrum;
	}

	public void setIsExceptionSpectrum(String isExceptionSpectrum) {
		this.isExceptionSpectrum = isExceptionSpectrum;
	}

	@Column(name = "electronic_under_Angle", precision = 5, scale = 1)
	public Double getElectronicUnderAngle() {
		return this.electronicUnderAngle;
	}

	public void setElectronicUnderAngle(Double electronicUnderAngle) {
		this.electronicUnderAngle = electronicUnderAngle;
	}

	@Column(name = "is_exception_under_Angle", length = 2)
	public String getIsExceptionUnderAngle() {
		return this.isExceptionUnderAngle;
	}

	public void setIsExceptionUnderAngle(String isExceptionUnderAngle) {
		this.isExceptionUnderAngle = isExceptionUnderAngle;
	}

	@Column(name = "orientation_angle", length = 50)
	public String getOrientationAngle() {
		return this.orientationAngle;
	}

	public void setOrientationAngle(String orientationAngle) {
		this.orientationAngle = orientationAngle;
	}

	@Column(name = "is_exception_orientation_angle", length = 2)
	public String getIsExceptionOrientationAngle() {
		return this.isExceptionOrientationAngle;
	}

	public void setIsExceptionOrientationAngle(
			String isExceptionOrientationAngle) {
		this.isExceptionOrientationAngle = isExceptionOrientationAngle;
	}

	@Column(name = "antenna_hang_high", length = 50)
	public String getAntennaHangHigh() {
		return this.antennaHangHigh;
	}

	public void setAntennaHangHigh(String antennaHangHigh) {
		this.antennaHangHigh = antennaHangHigh;
	}

	@Column(name = "is_exception_hang_high", length = 2)
	public String getIsExceptionHangHigh() {
		return this.isExceptionHangHigh;
	}

	public void setIsExceptionHangHigh(String isExceptionHangHigh) {
		this.isExceptionHangHigh = isExceptionHangHigh;
	}

	@Column(name = "altitude", length = 50)
	public String getAltitude() {
		return this.altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	@Column(name = "is_exception_altitude", length = 2)
	public String getIsExceptionAltitude() {
		return this.isExceptionAltitude;
	}

	public void setIsExceptionAltitude(String isExceptionAltitude) {
		this.isExceptionAltitude = isExceptionAltitude;
	}

}