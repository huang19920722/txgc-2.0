package com.yp.sys.pojo;

public class AntennaBean {
	
	private Long id;//天线id
	private String antennaType;//天线类型
	private String wirelessEquipmentManufacturer;//无线设备厂家
	private String antennaManufacturer;//馈线厂家
	private String antennaLength;//馈线长度
	private String isManually;//天线信息是否手动录入
	private String stationName;//基站名称
	private String lac;
	public String ci;//CI
	public String getAntennaType() {
		return antennaType;
	}
	public void setAntennaType(String antennaType) {
		this.antennaType = antennaType;
	}
	public String getAntennaManufacturer() {
		return antennaManufacturer;
	}
	public void setAntennaManufacturer(String antennaManufacture) {
		this.antennaManufacturer= antennaManufacture;
	}
	public String getAntennaLength() {
		return antennaLength;
	}
	public void setAntennaLength(String antennaLength) {
		this.antennaLength = antennaLength;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIsManually() {
		return isManually;
	}
	public void setIsManually(String isManually) {
		this.isManually = isManually;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getLac() {
		return lac;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getWirelessEquipmentManufacturer() {
		return wirelessEquipmentManufacturer;
	}
	public void setWirelessEquipmentManufacturer(
			String wirelessEquipmentManufacturer) {
		this.wirelessEquipmentManufacturer = wirelessEquipmentManufacturer;
	}
	
	
	
}
