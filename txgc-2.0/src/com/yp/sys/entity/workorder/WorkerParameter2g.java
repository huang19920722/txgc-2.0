package com.yp.sys.entity.workorder;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCyhcWorkerParameter2g entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_worker_parameter_2g", catalog = "txgc")
public class WorkerParameter2g implements java.io.Serializable {

	// Fields

	private Integer id;
	private String wirelessEquipmentManufacturer;
	private String communityName;
	private String stationType;
	private String name;
	private String transmissionMedium;
	private String isCity;
	private String communityScrambler;
	private String antennaType;
	private String frequencyLoadOne;
	private String frequencyLoadTwo;
	private String frequencyLoadThree;
	private Integer frequencyLoadNum;
	private Double longitude;
	private Double latitude;
	private String antennaManufacturer;
	private String antennaLength;
	private String spectrum;
	private Double mechanicalUnderAngle;
	private Double electronicUnderAngle;
	private String orientationAngle;
	private String antennaHangHigh;
	private String altitude;
	private String trafficArea;
	private String beautifyWay;
	private String coverType;
	private String coverage;
	private String standWay;
	private String logoutTime;
	private String supportType;
	private String openTime;
	private String detailAdress;
	private String isPublicRru;
	private String isPublicAntenna;
	private String isResist;
	private String isAntennaPutOutside;
	private String isIdlePlatform;
	private String isPublicAdress;
	private String isScrapCrooked;
	private String antennaSupportType;
	private String serrickHeight;
	private String antennaModel;
	private Double totalUnderAngle;
	private String antennaManufacturer2;
	private Double levelPowerAngle;
	private Double verticalPowerAngle;
	private Double inlayUnderAngle;
	private String isDoubleFrequencyAntenna;
	private String antennaGain;
	private Integer searchStarNum;
	private String lastUpdateTime;
	private String surveyType;
	private String gridArea;
	private String recentlyReportedTerminal;
	private String electromagneticDisturbIntension;
	private String lac;
	private String ci;
	private Integer orgId;
	private Timestamp synchronizationTime;
	private String translateState;
	private String signalStrength;
	private String rxqual;
	private String pci;
	private String bcch;
	private String rsrq;
	private String bsic;
	private String sinr;
	private String feq;
	private Double rollAngle;
	private String rsrp;
	private Integer inCount;

	// Constructors

	/** default constructor */
	public WorkerParameter2g() {
	}

	/** minimal constructor */
	public WorkerParameter2g(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public WorkerParameter2g(Integer id,
			String wirelessEquipmentManufacturer, String communityName,
			String stationType, String name, String transmissionMedium,
			String isCity, String communityScrambler, String antennaType,
			String frequencyLoadOne, String frequencyLoadTwo,
			String frequencyLoadThree, Integer frequencyLoadNum,
			Double longitude, Double latitude, String antennaManufacturer,
			String antennaLength, String spectrum, Double mechanicalUnderAngle,
			Double electronicUnderAngle, String orientationAngle,
			String antennaHangHigh, String altitude, String trafficArea,
			String beautifyWay, String coverType, String coverage,
			String standWay, String logoutTime, String supportType,
			String openTime, String detailAdress, String isPublicRru,
			String isPublicAntenna, String isResist,
			String isAntennaPutOutside, String isIdlePlatform,
			String isPublicAdress, String isScrapCrooked,
			String antennaSupportType, String serrickHeight,
			String antennaModel, Double totalUnderAngle,
			String antennaManufacturer2, Double levelPowerAngle,
			Double verticalPowerAngle, Double inlayUnderAngle,
			String isDoubleFrequencyAntenna, String antennaGain,
			Integer searchStarNum, String lastUpdateTime, String surveyType,
			String gridArea, String recentlyReportedTerminal,
			String electromagneticDisturbIntension, String lac, String ci,
			Integer orgId, Timestamp synchronizationTime,
			String translateState, String signalStrength, String rxqual,
			String pci, String bcch, String rsrq, String bsic, String sinr,
			String feq, Double rollAngle, String rsrp, Integer inCount) {
		this.id = id;
		this.wirelessEquipmentManufacturer = wirelessEquipmentManufacturer;
		this.communityName = communityName;
		this.stationType = stationType;
		this.name = name;
		this.transmissionMedium = transmissionMedium;
		this.isCity = isCity;
		this.communityScrambler = communityScrambler;
		this.antennaType = antennaType;
		this.frequencyLoadOne = frequencyLoadOne;
		this.frequencyLoadTwo = frequencyLoadTwo;
		this.frequencyLoadThree = frequencyLoadThree;
		this.frequencyLoadNum = frequencyLoadNum;
		this.longitude = longitude;
		this.latitude = latitude;
		this.antennaManufacturer = antennaManufacturer;
		this.antennaLength = antennaLength;
		this.spectrum = spectrum;
		this.mechanicalUnderAngle = mechanicalUnderAngle;
		this.electronicUnderAngle = electronicUnderAngle;
		this.orientationAngle = orientationAngle;
		this.antennaHangHigh = antennaHangHigh;
		this.altitude = altitude;
		this.trafficArea = trafficArea;
		this.beautifyWay = beautifyWay;
		this.coverType = coverType;
		this.coverage = coverage;
		this.standWay = standWay;
		this.logoutTime = logoutTime;
		this.supportType = supportType;
		this.openTime = openTime;
		this.detailAdress = detailAdress;
		this.isPublicRru = isPublicRru;
		this.isPublicAntenna = isPublicAntenna;
		this.isResist = isResist;
		this.isAntennaPutOutside = isAntennaPutOutside;
		this.isIdlePlatform = isIdlePlatform;
		this.isPublicAdress = isPublicAdress;
		this.isScrapCrooked = isScrapCrooked;
		this.antennaSupportType = antennaSupportType;
		this.serrickHeight = serrickHeight;
		this.antennaModel = antennaModel;
		this.totalUnderAngle = totalUnderAngle;
		this.antennaManufacturer2 = antennaManufacturer2;
		this.levelPowerAngle = levelPowerAngle;
		this.verticalPowerAngle = verticalPowerAngle;
		this.inlayUnderAngle = inlayUnderAngle;
		this.isDoubleFrequencyAntenna = isDoubleFrequencyAntenna;
		this.antennaGain = antennaGain;
		this.searchStarNum = searchStarNum;
		this.lastUpdateTime = lastUpdateTime;
		this.surveyType = surveyType;
		this.gridArea = gridArea;
		this.recentlyReportedTerminal = recentlyReportedTerminal;
		this.electromagneticDisturbIntension = electromagneticDisturbIntension;
		this.lac = lac;
		this.ci = ci;
		this.orgId = orgId;
		this.synchronizationTime = synchronizationTime;
		this.translateState = translateState;
		this.signalStrength = signalStrength;
		this.rxqual = rxqual;
		this.pci = pci;
		this.bcch = bcch;
		this.rsrq = rsrq;
		this.bsic = bsic;
		this.sinr = sinr;
		this.feq = feq;
		this.rollAngle = rollAngle;
		this.rsrp = rsrp;
		this.inCount = inCount;
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

	@Column(name = "wireless_equipment_manufacturer", length = 50)
	public String getWirelessEquipmentManufacturer() {
		return this.wirelessEquipmentManufacturer;
	}

	public void setWirelessEquipmentManufacturer(
			String wirelessEquipmentManufacturer) {
		this.wirelessEquipmentManufacturer = wirelessEquipmentManufacturer;
	}

	@Column(name = "community_name", length = 50)
	public String getCommunityName() {
		return this.communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	@Column(name = "station_type", length = 50)
	public String getStationType() {
		return this.stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "transmission_medium", length = 50)
	public String getTransmissionMedium() {
		return this.transmissionMedium;
	}

	public void setTransmissionMedium(String transmissionMedium) {
		this.transmissionMedium = transmissionMedium;
	}

	@Column(name = "is_city", length = 50)
	public String getIsCity() {
		return this.isCity;
	}

	public void setIsCity(String isCity) {
		this.isCity = isCity;
	}

	@Column(name = "community_scrambler", length = 50)
	public String getCommunityScrambler() {
		return this.communityScrambler;
	}

	public void setCommunityScrambler(String communityScrambler) {
		this.communityScrambler = communityScrambler;
	}

	@Column(name = "antenna_type", length = 50)
	public String getAntennaType() {
		return this.antennaType;
	}

	public void setAntennaType(String antennaType) {
		this.antennaType = antennaType;
	}

	@Column(name = "frequency_load_one", length = 50)
	public String getFrequencyLoadOne() {
		return this.frequencyLoadOne;
	}

	public void setFrequencyLoadOne(String frequencyLoadOne) {
		this.frequencyLoadOne = frequencyLoadOne;
	}

	@Column(name = "frequency_load_two", length = 50)
	public String getFrequencyLoadTwo() {
		return this.frequencyLoadTwo;
	}

	public void setFrequencyLoadTwo(String frequencyLoadTwo) {
		this.frequencyLoadTwo = frequencyLoadTwo;
	}

	@Column(name = "frequency_load_three", length = 50)
	public String getFrequencyLoadThree() {
		return this.frequencyLoadThree;
	}

	public void setFrequencyLoadThree(String frequencyLoadThree) {
		this.frequencyLoadThree = frequencyLoadThree;
	}

	@Column(name = "frequency_load_num")
	public Integer getFrequencyLoadNum() {
		return this.frequencyLoadNum;
	}

	public void setFrequencyLoadNum(Integer frequencyLoadNum) {
		this.frequencyLoadNum = frequencyLoadNum;
	}

	@Column(name = "longitude", precision = 9, scale = 5)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", precision = 9, scale = 5)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "antenna_manufacturer", length = 50)
	public String getAntennaManufacturer() {
		return this.antennaManufacturer;
	}

	public void setAntennaManufacturer(String antennaManufacturer) {
		this.antennaManufacturer = antennaManufacturer;
	}

	@Column(name = "antenna_length", length = 50)
	public String getAntennaLength() {
		return this.antennaLength;
	}

	public void setAntennaLength(String antennaLength) {
		this.antennaLength = antennaLength;
	}

	@Column(name = "spectrum", length = 50)
	public String getSpectrum() {
		return this.spectrum;
	}

	public void setSpectrum(String spectrum) {
		this.spectrum = spectrum;
	}

	@Column(name = "mechanical_under_Angle", precision = 5, scale = 1)
	public Double getMechanicalUnderAngle() {
		return this.mechanicalUnderAngle;
	}

	public void setMechanicalUnderAngle(Double mechanicalUnderAngle) {
		this.mechanicalUnderAngle = mechanicalUnderAngle;
	}

	@Column(name = "electronic_under_Angle", precision = 5, scale = 1)
	public Double getElectronicUnderAngle() {
		return this.electronicUnderAngle;
	}

	public void setElectronicUnderAngle(Double electronicUnderAngle) {
		this.electronicUnderAngle = electronicUnderAngle;
	}

	@Column(name = "orientation_angle", length = 50)
	public String getOrientationAngle() {
		return this.orientationAngle;
	}

	public void setOrientationAngle(String orientationAngle) {
		this.orientationAngle = orientationAngle;
	}

	@Column(name = "antenna_hang_high", length = 50)
	public String getAntennaHangHigh() {
		return this.antennaHangHigh;
	}

	public void setAntennaHangHigh(String antennaHangHigh) {
		this.antennaHangHigh = antennaHangHigh;
	}

	@Column(name = "altitude", length = 50)
	public String getAltitude() {
		return this.altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	@Column(name = "traffic_area", length = 50)
	public String getTrafficArea() {
		return this.trafficArea;
	}

	public void setTrafficArea(String trafficArea) {
		this.trafficArea = trafficArea;
	}

	@Column(name = "beautify_way", length = 50)
	public String getBeautifyWay() {
		return this.beautifyWay;
	}

	public void setBeautifyWay(String beautifyWay) {
		this.beautifyWay = beautifyWay;
	}

	@Column(name = "cover_type", length = 50)
	public String getCoverType() {
		return this.coverType;
	}

	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}

	@Column(name = "coverage", length = 50)
	public String getCoverage() {
		return this.coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	@Column(name = "stand_way", length = 50)
	public String getStandWay() {
		return this.standWay;
	}

	public void setStandWay(String standWay) {
		this.standWay = standWay;
	}

	@Column(name = "logout_time", length = 50)
	public String getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Column(name = "support_type", length = 50)
	public String getSupportType() {
		return this.supportType;
	}

	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}

	@Column(name = "open_time", length = 50)
	public String getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	@Column(name = "detail_adress", length = 50)
	public String getDetailAdress() {
		return this.detailAdress;
	}

	public void setDetailAdress(String detailAdress) {
		this.detailAdress = detailAdress;
	}

	@Column(name = "is_public_RRU", length = 50)
	public String getIsPublicRru() {
		return this.isPublicRru;
	}

	public void setIsPublicRru(String isPublicRru) {
		this.isPublicRru = isPublicRru;
	}

	@Column(name = "is_public_antenna", length = 50)
	public String getIsPublicAntenna() {
		return this.isPublicAntenna;
	}

	public void setIsPublicAntenna(String isPublicAntenna) {
		this.isPublicAntenna = isPublicAntenna;
	}

	@Column(name = "is_resist", length = 50)
	public String getIsResist() {
		return this.isResist;
	}

	public void setIsResist(String isResist) {
		this.isResist = isResist;
	}

	@Column(name = "is_antenna_put_outside", length = 50)
	public String getIsAntennaPutOutside() {
		return this.isAntennaPutOutside;
	}

	public void setIsAntennaPutOutside(String isAntennaPutOutside) {
		this.isAntennaPutOutside = isAntennaPutOutside;
	}

	@Column(name = "is_Idle_platform", length = 50)
	public String getIsIdlePlatform() {
		return this.isIdlePlatform;
	}

	public void setIsIdlePlatform(String isIdlePlatform) {
		this.isIdlePlatform = isIdlePlatform;
	}

	@Column(name = "is_public_adress", length = 50)
	public String getIsPublicAdress() {
		return this.isPublicAdress;
	}

	public void setIsPublicAdress(String isPublicAdress) {
		this.isPublicAdress = isPublicAdress;
	}

	@Column(name = "is_scrap_crooked", length = 50)
	public String getIsScrapCrooked() {
		return this.isScrapCrooked;
	}

	public void setIsScrapCrooked(String isScrapCrooked) {
		this.isScrapCrooked = isScrapCrooked;
	}

	@Column(name = "antenna_support_type", length = 50)
	public String getAntennaSupportType() {
		return this.antennaSupportType;
	}

	public void setAntennaSupportType(String antennaSupportType) {
		this.antennaSupportType = antennaSupportType;
	}

	@Column(name = "serrick_height", length = 50)
	public String getSerrickHeight() {
		return this.serrickHeight;
	}

	public void setSerrickHeight(String serrickHeight) {
		this.serrickHeight = serrickHeight;
	}

	@Column(name = "antenna_model", length = 50)
	public String getAntennaModel() {
		return this.antennaModel;
	}

	public void setAntennaModel(String antennaModel) {
		this.antennaModel = antennaModel;
	}

	@Column(name = "total_under_Angle", precision = 5, scale = 1)
	public Double getTotalUnderAngle() {
		return this.totalUnderAngle;
	}

	public void setTotalUnderAngle(Double totalUnderAngle) {
		this.totalUnderAngle = totalUnderAngle;
	}

	@Column(name = "antenna_manufacturer2", length = 50)
	public String getAntennaManufacturer2() {
		return this.antennaManufacturer2;
	}

	public void setAntennaManufacturer2(String antennaManufacturer2) {
		this.antennaManufacturer2 = antennaManufacturer2;
	}

	@Column(name = "level_power_angle", precision = 5, scale = 1)
	public Double getLevelPowerAngle() {
		return this.levelPowerAngle;
	}

	public void setLevelPowerAngle(Double levelPowerAngle) {
		this.levelPowerAngle = levelPowerAngle;
	}

	@Column(name = "vertical_power_angle", precision = 5, scale = 1)
	public Double getVerticalPowerAngle() {
		return this.verticalPowerAngle;
	}

	public void setVerticalPowerAngle(Double verticalPowerAngle) {
		this.verticalPowerAngle = verticalPowerAngle;
	}

	@Column(name = "inlay_under_angle", precision = 5, scale = 1)
	public Double getInlayUnderAngle() {
		return this.inlayUnderAngle;
	}

	public void setInlayUnderAngle(Double inlayUnderAngle) {
		this.inlayUnderAngle = inlayUnderAngle;
	}

	@Column(name = "is_double_frequency_antenna", length = 50)
	public String getIsDoubleFrequencyAntenna() {
		return this.isDoubleFrequencyAntenna;
	}

	public void setIsDoubleFrequencyAntenna(String isDoubleFrequencyAntenna) {
		this.isDoubleFrequencyAntenna = isDoubleFrequencyAntenna;
	}

	@Column(name = "antenna_gain", length = 50)
	public String getAntennaGain() {
		return this.antennaGain;
	}

	public void setAntennaGain(String antennaGain) {
		this.antennaGain = antennaGain;
	}

	@Column(name = "search_star_num")
	public Integer getSearchStarNum() {
		return this.searchStarNum;
	}

	public void setSearchStarNum(Integer searchStarNum) {
		this.searchStarNum = searchStarNum;
	}

	@Column(name = "last_update_time", length = 50)
	public String getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Column(name = "survey_type", length = 50)
	public String getSurveyType() {
		return this.surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}

	@Column(name = "grid_area", length = 50)
	public String getGridArea() {
		return this.gridArea;
	}

	public void setGridArea(String gridArea) {
		this.gridArea = gridArea;
	}

	@Column(name = "Recently_reported_terminal", length = 50)
	public String getRecentlyReportedTerminal() {
		return this.recentlyReportedTerminal;
	}

	public void setRecentlyReportedTerminal(String recentlyReportedTerminal) {
		this.recentlyReportedTerminal = recentlyReportedTerminal;
	}

	@Column(name = "electromagnetic_disturb_intension", length = 50)
	public String getElectromagneticDisturbIntension() {
		return this.electromagneticDisturbIntension;
	}

	public void setElectromagneticDisturbIntension(
			String electromagneticDisturbIntension) {
		this.electromagneticDisturbIntension = electromagneticDisturbIntension;
	}

	@Column(name = "LAC", length = 50)
	public String getLac() {
		return this.lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	@Column(name = "CI", length = 50)
	public String getCi() {
		return this.ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	@Column(name = "org_id")
	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "synchronization_time", length = 19)
	public Timestamp getSynchronizationTime() {
		return this.synchronizationTime;
	}

	public void setSynchronizationTime(Timestamp synchronizationTime) {
		this.synchronizationTime = synchronizationTime;
	}

	@Column(name = "translate_state", length = 5)
	public String getTranslateState() {
		return this.translateState;
	}

	public void setTranslateState(String translateState) {
		this.translateState = translateState;
	}

	@Column(name = "signal_strength", length = 50)
	public String getSignalStrength() {
		return this.signalStrength;
	}

	public void setSignalStrength(String signalStrength) {
		this.signalStrength = signalStrength;
	}

	@Column(name = "RXQUAL", length = 50)
	public String getRxqual() {
		return this.rxqual;
	}

	public void setRxqual(String rxqual) {
		this.rxqual = rxqual;
	}

	@Column(name = "PCI", length = 50)
	public String getPci() {
		return this.pci;
	}

	public void setPci(String pci) {
		this.pci = pci;
	}

	@Column(name = "BCCH", length = 50)
	public String getBcch() {
		return this.bcch;
	}

	public void setBcch(String bcch) {
		this.bcch = bcch;
	}

	@Column(name = "RSRQ", length = 50)
	public String getRsrq() {
		return this.rsrq;
	}

	public void setRsrq(String rsrq) {
		this.rsrq = rsrq;
	}

	@Column(name = "BSIC", length = 50)
	public String getBsic() {
		return this.bsic;
	}

	public void setBsic(String bsic) {
		this.bsic = bsic;
	}

	@Column(name = "SINR", length = 50)
	public String getSinr() {
		return this.sinr;
	}

	public void setSinr(String sinr) {
		this.sinr = sinr;
	}

	@Column(name = "FEQ", length = 50)
	public String getFeq() {
		return this.feq;
	}

	public void setFeq(String feq) {
		this.feq = feq;
	}

	@Column(name = "roll_angle", precision = 5, scale = 1)
	public Double getRollAngle() {
		return this.rollAngle;
	}

	public void setRollAngle(Double rollAngle) {
		this.rollAngle = rollAngle;
	}

	@Column(name = "RSRP", length = 50)
	public String getRsrp() {
		return this.rsrp;
	}

	public void setRsrp(String rsrp) {
		this.rsrp = rsrp;
	}

	@Column(name = "in_count")
	public Integer getInCount() {
		return this.inCount;
	}

	public void setInCount(Integer inCount) {
		this.inCount = inCount;
	}

}