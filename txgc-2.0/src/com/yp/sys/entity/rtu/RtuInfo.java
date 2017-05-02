package com.yp.sys.entity.rtu;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TCyhcRtuInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_rtu_list_info")
public class RtuInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private Double longitude;
	private Double latitude;
	private Double elevation;
	private Double baselineLength;
	private Double gpsCourseAngle;
	private Double pitchingAngle;
	private Double rollAngle;
	private Double mpCourseAngle;
	private Long utc;
	private Long acceleratedSpeedX;
	private Long acceleratedSpeedY;
	private Long acceleratedSpeedZ;
	private Long gyroscopeX;
	private Long gyroscopeY;
	private Long gyroscopeZ;
	private Double temperature;
	private Long magneticX;
	private Long magneticY;
	private Long magneticZ;
	private Long gps1SatelliteNum;
	private Long gps2SatelliteNum;
	private Long locationLogo;
	private Long courseAngleLogo;
	private Timestamp uploadTime;

	// Constructors



	/** default constructor */
	public RtuInfo() {
	}

	/** minimal constructor */
	public RtuInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public RtuInfo(Long id, Double longitude, Double latitude,
			Double elevation, Double baselineLength, Double gpsCourseAngle,
			Double pitchingAngle, Double rollAngle, Double mpCourseAngle,
			Long utc, Long acceleratedSpeedX, Long acceleratedSpeedY,
			Long acceleratedSpeedZ, Long gyroscopeX, Long gyroscopeY,
			Long gyroscopeZ, Double temperature, Long magneticX, Long magneticY,
			Long magneticZ, Long gps1SatelliteNum, Long gps2SatelliteNum,
			Long locationLogo, Long courseAngleLogo) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.elevation = elevation;
		this.baselineLength = baselineLength;
		this.gpsCourseAngle = gpsCourseAngle;
		this.pitchingAngle = pitchingAngle;
		this.rollAngle = rollAngle;
		this.mpCourseAngle = mpCourseAngle;
		this.utc = utc;
		this.acceleratedSpeedX = acceleratedSpeedX;
		this.acceleratedSpeedY = acceleratedSpeedY;
		this.acceleratedSpeedZ = acceleratedSpeedZ;
		this.gyroscopeX = gyroscopeX;
		this.gyroscopeY = gyroscopeY;
		this.gyroscopeZ = gyroscopeZ;
		this.temperature = temperature;
		this.magneticX = magneticX;
		this.magneticY = magneticY;
		this.magneticZ = magneticZ;
		this.gps1SatelliteNum = gps1SatelliteNum;
		this.gps2SatelliteNum = gps2SatelliteNum;
		this.locationLogo = locationLogo;
		this.courseAngleLogo = courseAngleLogo;
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

	@Column(name = "longitude", precision = 10, scale = 6)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", precision = 10, scale = 6)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "elevation", precision = 10, scale = 6)
	public Double getElevation() {
		return this.elevation;
	}

	public void setElevation(Double elevation) {
		this.elevation = elevation;
	}

	@Column(name = "baseline_length", precision = 10, scale = 6)
	public Double getBaselineLength() {
		return this.baselineLength;
	}

	public void setBaselineLength(Double baselineLength) {
		this.baselineLength = baselineLength;
	}

	@Column(name = "gps_course_angle", precision = 10, scale = 6)
	public Double getGpsCourseAngle() {
		return this.gpsCourseAngle;
	}

	public void setGpsCourseAngle(Double gpsCourseAngle) {
		this.gpsCourseAngle = gpsCourseAngle;
	}

	@Column(name = "pitching_angle", precision = 10, scale = 6)
	public Double getPitchingAngle() {
		return this.pitchingAngle;
	}

	public void setPitchingAngle(Double pitchingAngle) {
		this.pitchingAngle = pitchingAngle;
	}

	@Column(name = "roll_angle", precision = 10, scale = 6)
	public Double getRollAngle() {
		return this.rollAngle;
	}

	public void setRollAngle(Double rollAngle) {
		this.rollAngle = rollAngle;
	}

	@Column(name = "mp_course_angle", precision = 10, scale = 6)
	public Double getMpCourseAngle() {
		return this.mpCourseAngle;
	}

	public void setMpCourseAngle(Double mpCourseAngle) {
		this.mpCourseAngle = mpCourseAngle;
	}

	@Column(name = "utc")
	public Long getUtc() {
		return this.utc;
	}

	public void setUtc(Long utc) {
		this.utc = utc;
	}

	@Column(name = "accelerated_speed_x")
	public Long getAcceleratedSpeedX() {
		return this.acceleratedSpeedX;
	}

	public void setAcceleratedSpeedX(Long acceleratedSpeedX) {
		this.acceleratedSpeedX = acceleratedSpeedX;
	}

	@Column(name = "accelerated_speed_y")
	public Long getAcceleratedSpeedY() {
		return this.acceleratedSpeedY;
	}

	public void setAcceleratedSpeedY(Long acceleratedSpeedY) {
		this.acceleratedSpeedY = acceleratedSpeedY;
	}

	@Column(name = "accelerated_speed_z")
	public Long getAcceleratedSpeedZ() {
		return this.acceleratedSpeedZ;
	}

	public void setAcceleratedSpeedZ(Long acceleratedSpeedZ) {
		this.acceleratedSpeedZ = acceleratedSpeedZ;
	}

	@Column(name = "gyroscope_x")
	public Long getGyroscopeX() {
		return this.gyroscopeX;
	}

	public void setGyroscopeX(Long gyroscopeX) {
		this.gyroscopeX = gyroscopeX;
	}

	@Column(name = "gyroscope_y")
	public Long getGyroscopeY() {
		return this.gyroscopeY;
	}

	public void setGyroscopeY(Long gyroscopeY) {
		this.gyroscopeY = gyroscopeY;
	}

	@Column(name = "gyroscope_z")
	public Long getGyroscopeZ() {
		return this.gyroscopeZ;
	}

	public void setGyroscopeZ(Long gyroscopeZ) {
		this.gyroscopeZ = gyroscopeZ;
	}

	@Column(name = "temperature")
	public Double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	@Column(name = "magnetic_x")
	public Long getMagneticX() {
		return this.magneticX;
	}

	public void setMagneticX(Long magneticX) {
		this.magneticX = magneticX;
	}

	@Column(name = "magnetic_y")
	public Long getMagneticY() {
		return this.magneticY;
	}

	public void setMagneticY(Long magneticY) {
		this.magneticY = magneticY;
	}

	@Column(name = "magnetic_z")
	public Long getMagneticZ() {
		return this.magneticZ;
	}

	public void setMagneticZ(Long magneticZ) {
		this.magneticZ = magneticZ;
	}

	@Column(name = "gps1_satellite_num")
	public Long getGps1SatelliteNum() {
		return this.gps1SatelliteNum;
	}

	public void setGps1SatelliteNum(Long gps1SatelliteNum) {
		this.gps1SatelliteNum = gps1SatelliteNum;
	}

	@Column(name = "gps2_satellite_num")
	public Long getGps2SatelliteNum() {
		return this.gps2SatelliteNum;
	}

	public void setGps2SatelliteNum(Long gps2SatelliteNum) {
		this.gps2SatelliteNum = gps2SatelliteNum;
	}

	@Column(name = "location_logo")
	public Long getLocationLogo() {
		return this.locationLogo;
	}

	public void setLocationLogo(Long locationLogo) {
		this.locationLogo = locationLogo;
	}

	@Column(name = "course_angle_logo")
	public Long getCourseAngleLogo() {
		return this.courseAngleLogo;
	}

	public void setCourseAngleLogo(Long courseAngleLogo) {
		this.courseAngleLogo = courseAngleLogo;
	}
	
	@Column(name = "upload_time")
	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

}