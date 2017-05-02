package com.yp.sys.entity.collect;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.yp.sys.entity.organization.Organization;
import com.yp.sys.entity.workorder.CollectCompareInfo;
import com.yp.sys.entity.workorder.WorkOrderInfo;

/**   
 * 文件名称：工参采集信息
 * 内容摘要： 
 * 创建人： huangmingxing
 * 创建日期： 2016年11月20日
 * 版本号： v1.0.0
 * 公  司：重邮汇侧
 * 版权所有： (C)2001-2016     
 * 修改记录1 
 * 修改日期：
 * 版本号：
 * 修改人：
 * 修改内容：  
 **/ 

@SuppressWarnings("serial")
@Entity
@Table(name = "t_cyhc_collect_info")
public class CollectInfo implements java.io.Serializable {

	private Long id;
	private String communityScrambler;//小区识别码
	private WorkOrderInfo workOrder;//工单ID
	private Long terminalId;//采集终端ID
	private Long workParameterId;//工参编号
	private String communityName;//小区名称
	private String stationName;
	private Double longitude;
	private Double latitude;
	private String spectrum;
	private Double electronicUnderAngle;
	private Double orientationAngle;
	private Double antennaHangHigh;
	private Double altitude;
	private String trafficArea;
	private String beautifyWay;
	private String supportType;
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
	private String antennaManufacturer;
	private Integer searchStarNum;
	private String surveyType;
	private String gridArea;
	private String recentReportedTerminal;
	private String electromagneticDisturbIntension;
	private String lac;
	private String ci;
	private Timestamp reportTime;
	private Timestamp testTime;
	private String tac;
	private String signalStrength;
	private String rxqual;
	private String pci;
	private String bcch;
	private String rsrq;
	private String bsic;
	private String sinr;
	private String feq;
	private Double rollAngle;
	private String terminalNum;
	private String opUser;
	private String remark;
	private String rsrp;
	private String frequencyPoint;
	private Double mechanicalUnderAngle;
	private String transArea;
	
	private Organization org;//org_id 机构id(运营商id)
	private String networkStandard;//network_standard
	private Double towerHeight;//tower_height
	private CollectCompareInfo collectCompare;//采集比较信息
	
	
	private String rssi;
	private String networkMode;
	private String is_city;
	private String levelDistance;
	private String antennaBarcode;
	private String surveyWay;
	private String enb;
	private String cell;
	private String antennaImpedance;
	private String polarizationMode;
	private String gain;
	private String levelHalfPowerAngle;
	private String verticalHalfPowerAngle;
	private String productionDate;


	

	// Constructors

	

	/** default constructor */
	public CollectInfo() {
	}

	/** minimal constructor */
	public CollectInfo(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CollectInfo(Long id, String communityScrambler,
			WorkOrderInfo workOrder, Long terminalId,
			Long workParameterId, String communityName, String stationName,
			Double longitude, Double latitude, String spectrum,
			Double electronicUnderAngle, Double orientationAngle,
			Double antennaHangHigh, Double altitude, String trafficArea,
			String beautifyWay, String supportType, String isPublicRru,
			String isPublicAntenna, String isResist,
			String isAntennaPutOutside, String isIdlePlatform,
			String isPublicAdress, String isScrapCrooked,
			String antennaSupportType, String serrickHeight,
			String antennaModel, String antennaManufacturer,
			Integer searchStarNum, String surveyType, String gridArea,
			String recentReportedTerminal,
			String electromagneticDisturbIntension, String lac, String ci,
			Timestamp reportTime, Timestamp testTime, String tac,
			String signalStrength, String rxqual, String pci, String bcch,
			String rsrq, String bsic, String sinr, String feq,
			Double rollAngle, String terminalNum, String opUser, String remark,
			String rsrp, String frequencyPoint, Double mechanicalUnderAngle,
			String transArea, Organization orgId, String networkStandard,
			Double towerHeight,String rssi,
			String networkMode, String level, String levelDistance,
			String antennaBarcode, String surveyWay, String enb, String cell,
			String antennaImpedance, String polarizationMode, String gain,
			String levelHalfPowerAngle, String verticalHalfPowerAngle,
			String productionDate) {
		this.id = id;
		this.communityScrambler = communityScrambler;
		this.workOrder = workOrder;
		this.terminalId = terminalId;
		this.workParameterId = workParameterId;
		this.communityName = communityName;
		this.stationName = stationName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.spectrum = spectrum;
		this.electronicUnderAngle = electronicUnderAngle;
		this.orientationAngle = orientationAngle;
		this.antennaHangHigh = antennaHangHigh;
		this.altitude = altitude;
		this.trafficArea = trafficArea;
		this.beautifyWay = beautifyWay;
		this.supportType = supportType;
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
		this.antennaManufacturer = antennaManufacturer;
		this.searchStarNum = searchStarNum;
		this.surveyType = surveyType;
		this.gridArea = gridArea;
		this.recentReportedTerminal = recentReportedTerminal;
		this.electromagneticDisturbIntension = electromagneticDisturbIntension;
		this.lac = lac;
		this.ci = ci;
		this.reportTime = reportTime;
		this.testTime = testTime;
		this.tac = tac;
		this.signalStrength = signalStrength;
		this.rxqual = rxqual;
		this.pci = pci;
		this.bcch = bcch;
		this.rsrq = rsrq;
		this.bsic = bsic;
		this.sinr = sinr;
		this.feq = feq;
		this.rollAngle = rollAngle;
		this.terminalNum = terminalNum;
		this.opUser = opUser;
		this.remark = remark;
		this.rsrp = rsrp;
		this.frequencyPoint = frequencyPoint;
		this.mechanicalUnderAngle = mechanicalUnderAngle;
		this.transArea = transArea;
		this.org = orgId;
		this.networkStandard = networkStandard;
		this.towerHeight = towerHeight;
		this.rssi = rssi;
		this.networkMode = networkMode;
		this.is_city = is_city;
		this.levelDistance = levelDistance;
		this.antennaBarcode = antennaBarcode;
		this.surveyWay = surveyWay;
		this.enb = enb;
		this.cell = cell;
		this.antennaImpedance = antennaImpedance;
		this.polarizationMode = polarizationMode;
		this.gain = gain;
		this.levelHalfPowerAngle = levelHalfPowerAngle;
		this.verticalHalfPowerAngle = verticalHalfPowerAngle;
		this.productionDate = productionDate;
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

	@Column(name = "community_scrambler")
	public String getCommunityScrambler() {
		return this.communityScrambler;
	}

	public void setCommunityScrambler(String communityScrambler) {
		this.communityScrambler = communityScrambler;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "work_order_id")
	public WorkOrderInfo getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrderInfo workOrder) {
		this.workOrder = workOrder;
	}
	
	@Column(name = "terminal_id")
	public Long getTerminalId() {
		return this.terminalId;
	}


	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name = "worker_parameter_id")
	public Long getWorkParameterId() {
		return this.workParameterId;
	}

	public void setWorkParameterId(Long workParameterId) {
		this.workParameterId = workParameterId;
	}

	@Column(name = "community_name")
	public String getCommunityName() {
		return this.communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	@Column(name = "station_name")
	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Column(name = "longitude")
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude")
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "spectrum")
	public String getSpectrum() {
		return this.spectrum;
	}

	public void setSpectrum(String spectrum) {
		this.spectrum = spectrum;
	}

	@Column(name = "electronic_under_Angle")
	public Double getElectronicUnderAngle() {
		return this.electronicUnderAngle;
	}

	public void setElectronicUnderAngle(Double electronicUnderAngle) {
		this.electronicUnderAngle = electronicUnderAngle;
	}

	@Column(name = "orientation_angle")
	public Double getOrientationAngle() {
		return this.orientationAngle;
	}

	public void setOrientationAngle(Double orientationAngle) {
		this.orientationAngle = orientationAngle;
	}

	@Column(name = "antenna_hang_high")
	public Double getAntennaHangHigh() {
		return this.antennaHangHigh;
	}

	public void setAntennaHangHigh(Double antennaHangHigh) {
		this.antennaHangHigh = antennaHangHigh;
	}

	@Column(name = "altitude")
	public Double getAltitude() {
		return this.altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	@Column(name = "traffic_area")
	public String getTrafficArea() {
		return this.trafficArea;
	}

	public void setTrafficArea(String trafficArea) {
		this.trafficArea = trafficArea;
	}

	@Column(name = "beautify_way")
	public String getBeautifyWay() {
		return this.beautifyWay;
	}

	public void setBeautifyWay(String beautifyWay) {
		this.beautifyWay = beautifyWay;
	}

	@Column(name = "support_type")
	public String getSupportType() {
		return this.supportType;
	}

	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}

	@Column(name = "is_public_RRU")
	public String getIsPublicRru() {
		return this.isPublicRru;
	}

	public void setIsPublicRru(String isPublicRru) {
		this.isPublicRru = isPublicRru;
	}

	@Column(name = "is_public_antenna")
	public String getIsPublicAntenna() {
		return this.isPublicAntenna;
	}

	public void setIsPublicAntenna(String isPublicAntenna) {
		this.isPublicAntenna = isPublicAntenna;
	}

	@Column(name = "is_resist")
	public String getIsResist() {
		return this.isResist;
	}

	public void setIsResist(String isResist) {
		this.isResist = isResist;
	}

	@Column(name = "is_antenna_put_outside")
	public String getIsAntennaPutOutside() {
		return this.isAntennaPutOutside;
	}

	public void setIsAntennaPutOutside(String isAntennaPutOutside) {
		this.isAntennaPutOutside = isAntennaPutOutside;
	}

	@Column(name = "is_Idle_platform")
	public String getIsIdlePlatform() {
		return this.isIdlePlatform;
	}

	public void setIsIdlePlatform(String isIdlePlatform) {
		this.isIdlePlatform = isIdlePlatform;
	}

	@Column(name = "is_public_adress")
	public String getIsPublicAdress() {
		return this.isPublicAdress;
	}

	public void setIsPublicAdress(String isPublicAdress) {
		this.isPublicAdress = isPublicAdress;
	}

	@Column(name = "is_scrap_crooked")
	public String getIsScrapCrooked() {
		return this.isScrapCrooked;
	}

	public void setIsScrapCrooked(String isScrapCrooked) {
		this.isScrapCrooked = isScrapCrooked;
	}

	@Column(name = "antenna_support_type")
	public String getAntennaSupportType() {
		return this.antennaSupportType;
	}

	public void setAntennaSupportType(String antennaSupportType) {
		this.antennaSupportType = antennaSupportType;
	}

	@Column(name = "serrick_height")
	public String getSerrickHeight() {
		return this.serrickHeight;
	}

	public void setSerrickHeight(String serrickHeight) {
		this.serrickHeight = serrickHeight;
	}

	@Column(name = "antenna_model")
	public String getAntennaModel() {
		return this.antennaModel;
	}

	public void setAntennaModel(String antennaModel) {
		this.antennaModel = antennaModel;
	}

	@Column(name = "antenna_manufacturer")
	public String getAntennaManufacturer() {
		return this.antennaManufacturer;
	}

	public void setAntennaManufacturer(String antennaManufacturer) {
		this.antennaManufacturer = antennaManufacturer;
	}

	@Column(name = "search_star_num")
	public Integer getSearchStarNum() {
		return this.searchStarNum;
	}

	public void setSearchStarNum(Integer searchStarNum) {
		this.searchStarNum = searchStarNum;
	}

	@Column(name = "survey_type")
	public String getSurveyType() {
		return this.surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}

	@Column(name = "grid_area")
	public String getGridArea() {
		return this.gridArea;
	}

	public void setGridArea(String gridArea) {
		this.gridArea = gridArea;
	}

	@Column(name = "recent_reported_terminal")
	public String getRecentReportedTerminal() {
		return this.recentReportedTerminal;
	}

	public void setRecentReportedTerminal(String recentReportedTerminal) {
		this.recentReportedTerminal = recentReportedTerminal;
	}

	@Column(name = "electromagnetic_disturb_intension")
	public String getElectromagneticDisturbIntension() {
		return this.electromagneticDisturbIntension;
	}

	public void setElectromagneticDisturbIntension(
			String electromagneticDisturbIntension) {
		this.electromagneticDisturbIntension = electromagneticDisturbIntension;
	}

	@Column(name = "LAC")
	public String getLac() {
		return this.lac;
	}

	public void setLac(String lac) {
		this.lac = lac;
	}

	@Column(name = "CI")
	public String getCi() {
		return this.ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	@Column(name = "report_time")
	public Timestamp getReportTime() {
		return this.reportTime;
	}

	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

	@Column(name = "test_time")
	public Timestamp getTestTime() {
		return this.testTime;
	}

	public void setTestTime(Timestamp testTime) {
		this.testTime = testTime;
	}

	@Column(name = "TAC")
	public String getTac() {
		return this.tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	@Column(name = "signal_strength")
	public String getSignalStrength() {
		return this.signalStrength;
	}

	public void setSignalStrength(String signalStrength) {
		this.signalStrength = signalStrength;
	}

	@Column(name = "RXQUAL")
	public String getRxqual() {
		return this.rxqual;
	}

	public void setRxqual(String rxqual) {
		this.rxqual = rxqual;
	}

	@Column(name = "PCI")
	public String getPci() {
		return this.pci;
	}

	public void setPci(String pci) {
		this.pci = pci;
	}

	@Column(name = "BCCH")
	public String getBcch() {
		return this.bcch;
	}

	public void setBcch(String bcch) {
		this.bcch = bcch;
	}

	@Column(name = "RSRQ")
	public String getRsrq() {
		return this.rsrq;
	}

	public void setRsrq(String rsrq) {
		this.rsrq = rsrq;
	}

	@Column(name = "BSIC")
	public String getBsic() {
		return this.bsic;
	}

	public void setBsic(String bsic) {
		this.bsic = bsic;
	}

	@Column(name = "SINR")
	public String getSinr() {
		return this.sinr;
	}

	public void setSinr(String sinr) {
		this.sinr = sinr;
	}

	@Column(name = "FEQ")
	public String getFeq() {
		return this.feq;
	}

	public void setFeq(String feq) {
		this.feq = feq;
	}

	@Column(name = "roll_angle")
	public Double getRollAngle() {
		return this.rollAngle;
	}

	public void setRollAngle(Double rollAngle) {
		this.rollAngle = rollAngle;
	}

	@Column(name = "terminal_num")
	public String getTerminalNum() {
		return this.terminalNum;
	}

	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}

	@Column(name = "op_user")
	public String getOpUser() {
		return this.opUser;
	}

	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "RSRP")
	public String getRsrp() {
		return this.rsrp;
	}

	public void setRsrp(String rsrp) {
		this.rsrp = rsrp;
	}

	@Column(name = "frequency_point")
	public String getFrequencyPoint() {
		return this.frequencyPoint;
	}

	public void setFrequencyPoint(String frequencyPoint) {
		this.frequencyPoint = frequencyPoint;
	}

	@Column(name = "mechanical_under_Angle")
	public Double getMechanicalUnderAngle() {
		return this.mechanicalUnderAngle;
	}

	public void setMechanicalUnderAngle(Double mechanicalUnderAngle) {
		this.mechanicalUnderAngle = mechanicalUnderAngle;
	}

	@Column(name = "trans_area")
	public String getTransArea() {
		return this.transArea;
	}

	public void setTransArea(String transArea) {
		this.transArea = transArea;
	}


	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "org_id")
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}



	@Column(name = "network_standard")
	public String getNetworkStandard() {
		return this.networkStandard;
	}

	public void setNetworkStandard(String networkStandard) {
		this.networkStandard = networkStandard;
	}

	@Column(name = "tower_height")
	public Double getTowerHeight() {
		return this.towerHeight;
	}

	public void setTowerHeight(Double towerHeight) {
		this.towerHeight = towerHeight;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "compare_id")
	public CollectCompareInfo getCollectCompare() {
		return collectCompare;
	}

	public void setCollectCompare(CollectCompareInfo collectCompare) {
		this.collectCompare = collectCompare;
	}
	
	@Column(name = "rssi", length = 50)
	public String getRssi() {
		return this.rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}

	@Column(name = "network_mode", length = 50)
	public String getNetworkMode() {
		return this.networkMode;
	}

	public void setNetworkMode(String networkMode) {
		this.networkMode = networkMode;
	}

	@Column(name = "is_city")
	public String getIs_city() {
		return is_city;
	}

	public void setIs_city(String is_city) {
		this.is_city = is_city;
	}
	@Column(name = "level_distance")
	public String getLevelDistance() {
		return this.levelDistance;
	}



	public void setLevelDistance(String levelDistance) {
		this.levelDistance = levelDistance;
	}

	@Column(name = "antenna_barcode", length = 50)
	public String getAntennaBarcode() {
		return this.antennaBarcode;
	}

	public void setAntennaBarcode(String antennaBarcode) {
		this.antennaBarcode = antennaBarcode;
	}

	@Column(name = "survey_way", length = 50)
	public String getSurveyWay() {
		return this.surveyWay;
	}

	public void setSurveyWay(String surveyWay) {
		this.surveyWay = surveyWay;
	}

	@Column(name = "enb", length = 50)
	public String getEnb() {
		return this.enb;
	}

	public void setEnb(String enb) {
		this.enb = enb;
	}

	@Column(name = "cell", length = 50)
	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	@Column(name = "antenna_impedance", length = 50)
	public String getAntennaImpedance() {
		return this.antennaImpedance;
	}

	public void setAntennaImpedance(String antennaImpedance) {
		this.antennaImpedance = antennaImpedance;
	}

	@Column(name = "polarization_mode", length = 50)
	public String getPolarizationMode() {
		return this.polarizationMode;
	}

	public void setPolarizationMode(String polarizationMode) {
		this.polarizationMode = polarizationMode;
	}

	@Column(name = "gain", length = 50)
	public String getGain() {
		return this.gain;
	}

	public void setGain(String gain) {
		this.gain = gain;
	}

	@Column(name = "level_half_power_angle", length = 50)
	public String getLevelHalfPowerAngle() {
		return this.levelHalfPowerAngle;
	}

	public void setLevelHalfPowerAngle(String levelHalfPowerAngle) {
		this.levelHalfPowerAngle = levelHalfPowerAngle;
	}

	@Column(name = "vertical_half_power_angle", length = 50)
	public String getVerticalHalfPowerAngle() {
		return this.verticalHalfPowerAngle;
	}

	public void setVerticalHalfPowerAngle(String verticalHalfPowerAngle) {
		this.verticalHalfPowerAngle = verticalHalfPowerAngle;
	}

	@Column(name = "production_date", length = 50)
	public String getProductionDate() {
		return this.productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}




}