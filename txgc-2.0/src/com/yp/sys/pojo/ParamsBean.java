package com.yp.sys.pojo;

import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


  
/**
 * 
 * 版权所有：2016-重庆重邮汇测通讯技术有限公司
 * 项目名称：txgc   
 *
 * 类描述：2G工参Bean对象
 * 类名称：com.yp.sys.pojo.ParamsBean     
 * 创建人：zhongyang
 * 创建时间：2016-11-16 下午3:13:09   
 * 修改人：
 * 修改时间：2016-11-16 下午3:13:09   
 * 修改备注：   
 * @version   V1.0
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class ParamsBean implements java.io.Serializable {
	
	
	private Long id;              //2g工参Id
	private String wirelessEquipmentManufacturer;        //无线设备厂家
	private String communityName;               //小区名称
	private String stationType;               //基站站型
	private String stationName;               //基站名称
	private String transmissionMedium;               //基站传输介质
	private String isCity;               //是否为城区
	private String communityScrambler;               //小区扰码(CPI)
	private String antennaType;               //天线类型
	private String frequencyLoadOne;               //频载1
	private String frequencyLoadTwo;               //频载2
	private String frequencyLoadThree;               //频载3
	private Integer frequencyLoadNum;               //频载数目
	private Double longitude;               //经度
	private Double latitude;               //纬度
	private String antennaManufacturer;               //馈线厂家
	private String antennaLength;               //馈线长度
	private String spectrum;               //频段

	private Double orientationAngle;               //方位角
	private Double antennaHangHigh;               //天线挂高
	private Double altitude;               //海拔高度
	private String trafficArea;               //话务区域
	private String beautifyWay;               //美化方式
	private String coverType;               //覆盖类型
	private String coverage;               //覆盖范围
	private String standWay;               //上站途径
	private Timestamp logoutTime;               //退网时间
	private String supportType;               //支撑类型
	private Timestamp openTime;               //基站开通时间
	private String detailAdress;               //基站详细地址

	private String isPublicAntenna;               //是否共天馈
	private String isResist;               //是否阻挡
	private String isAntennaPutOutside;               //是否天线外放

	private String isPublicAdress;               //与其他运营商共址情况
	private String isScrapCrooked;               //是否刮歪
	private String antennaSupportType;               //天线支撑类型
	private String serrickHeight;               //抱杆高度
	private String antennaModel;               //天线型号

	private Double levelPowerAngle;               //水平半功率角
	private Double verticalPowerAngle;               //垂直半功率角
	private Double inlayUnderAngle;               //内置下倾角
	private String isDoubleFrequencyAntenna;               //是否双频天线
	private String antennaGain;               //天线增益
	private Integer searchStarNum;               //搜星颗数
	private Timestamp lastUpdateTime;               //最后更新时间
	private String surveyType;               //勘察类型
	private String gridArea;               //网格区域

	private String electromagneticDisturbIntension;               //电磁干扰强度

	private Integer orgId;               //运营商ID
	private Timestamp synchronizationTime;               //同步时间
	private String translateState;               //上报状态
	private String signalStrength;               //RSRP（信号强度）

	private Double rollAngle;               //横滚角

	private Integer inCount;               //勘察次数
	private Long thresholdId;	//阀值ID
	private String isManually;	//天线信息是否手动录入
	private String recordStatus;	//记录状态
	private String antennaManufacturer2;               //天线厂家
	private String startTime;	//开始时间
	private String endTime;		//结束时间
	
	private String frequencyPoint;//频点
	private String rssi;//rssi
	private String networkMode;//网络模式
	
	private String levelDistance;//level_distance
	private String antennaBarcode;//天线条码
	private String surveyWay;//勘察方式
	private String enb;//enb
	private String cell;//cell
	private String antennaImpedance;//天线阻抗
	private String polarizationMode;//极化方式
	private String gain;//增益
	private String levelHalfPowerAngle;//水平半功率角
	private String verticalHalfPowerAngle;//垂直半功率角
	private String productionDate;//生产日期
	
	
	private Double mechanicalUnderAngle;// mechanicalUnderAngle;               //机械下倾角
	private Double electronicUnderAngle;//electronicUnderAngle; //电子下倾角
	private String lac;//lac;               //LAC
	private String ci;//ci;               //CI
	private String recentReportedTerminal;//recentlyReportedTerminal;               //最近上报终端
	private String isIdlePlatform;//isIdlePlatform;               //是否有闲置平台
	private Double totalUnderAngle;//totalUnderAngle;               //总下倾角
	private String isPublicRru;//isPublicRru;               //是否共RRU
	private String rsrp;//rsrp;               //RSRP
	private String rxqual;//rxqual;               //RXQUAL（通话质量）
	private String pci;//pci;               //PCI（物理小区标识）
	private String bcch;//bcch;               //BCCH（通用信息的信道）
	private String rsrq;//rsrq;               //RSRQ（信号质量）
	private String bsic;//bsic;               //BSIC（基站识别码）
	private String sinr;//sinr;               //SINR（信号信噪比）
	private String feq;//feq;               //FEQ
	
	

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
	public String getRsrp() {
		return rsrp;
	}
	public void setRsrp(String rsrp) {
		this.rsrp = rsrp;
	}
	public String getRxqual() {
		return rxqual;
	}
	public void setRxqual(String rxqual) {
		this.rxqual = rxqual;
	}
	public String getPci() {
		return pci;
	}
	public void setPci(String pci) {
		this.pci = pci;
	}
	public String getBcch() {
		return bcch;
	}
	public void setBcch(String bcch) {
		this.bcch = bcch;
	}
	public String getRsrq() {
		return rsrq;
	}
	public void setRsrq(String rsrq) {
		this.rsrq = rsrq;
	}
	public String getBsic() {
		return bsic;
	}
	public void setBsic(String bsic) {
		this.bsic = bsic;
	}
	public String getSinr() {
		return sinr;
	}
	public void setSinr(String sinr) {
		this.sinr = sinr;
	}
	public String getFeq() {
		return feq;
	}
	public void setFeq(String feq) {
		this.feq = feq;
	}
	public Double getMechanicalUnderAngle() {
		return mechanicalUnderAngle;
	}
	public void setMechanicalUnderAngle(Double mechanicalUnderAngle) {
		this.mechanicalUnderAngle = mechanicalUnderAngle;
	}
	public Double getElectronicUnderAngle() {
		return electronicUnderAngle;
	}
	public void setElectronicUnderAngle(Double electronicUnderAngle) {
		this.electronicUnderAngle = electronicUnderAngle;
	}
	public String getRecentReportedTerminal() {
		return recentReportedTerminal;
	}
	public void setRecentReportedTerminal(String recentReportedTerminal) {
		this.recentReportedTerminal = recentReportedTerminal;
	}

	public String getIsIdlePlatform() {
		return isIdlePlatform;
	}
	public void setIsIdlePlatform(String isIdlePlatform) {
		this.isIdlePlatform = isIdlePlatform;
	}

	
	public Double getTotalUnderAngle() {
		return totalUnderAngle;
	}
	public void setTotalUnderAngle(Double totalUnderAngle) {
		this.totalUnderAngle = totalUnderAngle;
	}
	public String getIsPublicRru() {
		return isPublicRru;
	}
	public void setIsPublicRru(String isPublicRru) {
		this.isPublicRru = isPublicRru;
	}
	public String getFrequencyPoint() {
		return frequencyPoint;
	}
	public void setFrequencyPoint(String frequencyPoint) {
		this.frequencyPoint = frequencyPoint;
	}
	public String getRssi() {
		return rssi;
	}
	public void setRssi(String rssi) {
		this.rssi = rssi;
	}
	public String getNetworkMode() {
		return networkMode;
	}
	public void setNetworkMode(String networkMode) {
		this.networkMode = networkMode;
	}
	public String getLevelDistance() {
		return levelDistance;
	}
	public void setLevelDistance(String levelDistance) {
		this.levelDistance = levelDistance;
	}
	public String getAntennaBarcode() {
		return antennaBarcode;
	}
	public void setAntennaBarcode(String antennaBarcode) {
		this.antennaBarcode = antennaBarcode;
	}
	public String getSurveyWay() {
		return surveyWay;
	}
	public void setSurveyWay(String surveyWay) {
		this.surveyWay = surveyWay;
	}
	public String getEnb() {
		return enb;
	}
	public void setEnb(String enb) {
		this.enb = enb;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getAntennaImpedance() {
		return antennaImpedance;
	}
	public void setAntennaImpedance(String antennaImpedance) {
		this.antennaImpedance = antennaImpedance;
	}
	public String getPolarizationMode() {
		return polarizationMode;
	}
	public void setPolarizationMode(String polarizationMode) {
		this.polarizationMode = polarizationMode;
	}
	public String getGain() {
		return gain;
	}
	public void setGain(String gain) {
		this.gain = gain;
	}
	public String getLevelHalfPowerAngle() {
		return levelHalfPowerAngle;
	}
	public void setLevelHalfPowerAngle(String levelHalfPowerAngle) {
		this.levelHalfPowerAngle = levelHalfPowerAngle;
	}
	public String getVerticalHalfPowerAngle() {
		return verticalHalfPowerAngle;
	}
	public void setVerticalHalfPowerAngle(String verticalHalfPowerAngle) {
		this.verticalHalfPowerAngle = verticalHalfPowerAngle;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	public String getIsManually() {
		return isManually;
	}
	public void setIsManually(String isManually) {
		this.isManually = isManually;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWirelessEquipmentManufacturer() {
		return wirelessEquipmentManufacturer;
	}
	public void setWirelessEquipmentManufacturer(
			String wirelessEquipmentManufacturer) {
		this.wirelessEquipmentManufacturer = wirelessEquipmentManufacturer;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getStationType() {
		return stationType;
	}
	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getTransmissionMedium() {
		return transmissionMedium;
	}
	public void setTransmissionMedium(String transmissionMedium) {
		this.transmissionMedium = transmissionMedium;
	}
	public String getIsCity() {
		return isCity;
	}
	public void setIsCity(String isCity) {
		this.isCity = isCity;
	}
	public String getCommunityScrambler() {
		return communityScrambler;
	}
	public void setCommunityScrambler(String communityScrambler) {
		this.communityScrambler = communityScrambler;
	}
	public String getAntennaType() {
		return antennaType;
	}
	public void setAntennaType(String antennaType) {
		this.antennaType = antennaType;
	}
	public String getFrequencyLoadOne() {
		return frequencyLoadOne;
	}
	public void setFrequencyLoadOne(String frequencyLoadOne) {
		this.frequencyLoadOne = frequencyLoadOne;
	}
	public String getFrequencyLoadTwo() {
		return frequencyLoadTwo;
	}
	public void setFrequencyLoadTwo(String frequencyLoadTwo) {
		this.frequencyLoadTwo = frequencyLoadTwo;
	}
	public String getFrequencyLoadThree() {
		return frequencyLoadThree;
	}
	public void setFrequencyLoadThree(String frequencyLoadThree) {
		this.frequencyLoadThree = frequencyLoadThree;
	}
	public Integer getFrequencyLoadNum() {
		return frequencyLoadNum;
	}
	public void setFrequencyLoadNum(Integer frequencyLoadNum) {
		this.frequencyLoadNum = frequencyLoadNum;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getAntennaManufacturer() {
		return antennaManufacturer;
	}
	public void setAntennaManufacturer(String antennaManufacturer) {
		this.antennaManufacturer = antennaManufacturer;
	}
	public String getAntennaLength() {
		return antennaLength;
	}
	public void setAntennaLength(String antennaLength) {
		this.antennaLength = antennaLength;
	}
	public String getSpectrum() {
		return spectrum;
	}
	public void setSpectrum(String spectrum) {
		this.spectrum = spectrum;
	}


	public Double getOrientationAngle() {
		return orientationAngle;
	}
	public void setOrientationAngle(Double orientationAngle) {
		this.orientationAngle = orientationAngle;
	}
	public Double getAntennaHangHigh() {
		return antennaHangHigh;
	}
	public void setAntennaHangHigh(Double antennaHangHigh) {
		this.antennaHangHigh = antennaHangHigh;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public String getTrafficArea() {
		return trafficArea;
	}
	public void setTrafficArea(String trafficArea) {
		this.trafficArea = trafficArea;
	}
	public String getBeautifyWay() {
		return beautifyWay;
	}
	public void setBeautifyWay(String beautifyWay) {
		this.beautifyWay = beautifyWay;
	}
	public String getCoverType() {
		return coverType;
	}
	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}
	public String getCoverage() {
		return coverage;
	}
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	public String getStandWay() {
		return standWay;
	}
	public void setStandWay(String standWay) {
		this.standWay = standWay;
	}
	
	public String getSupportType() {
		return supportType;
	}
	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}

	public String getDetailAdress() {
		return detailAdress;
	}
	public void setDetailAdress(String detailAdress) {
		this.detailAdress = detailAdress;
	}

	public String getIsPublicAntenna() {
		return isPublicAntenna;
	}
	public void setIsPublicAntenna(String isPublicAntenna) {
		this.isPublicAntenna = isPublicAntenna;
	}
	public String getIsResist() {
		return isResist;
	}
	public void setIsResist(String isResist) {
		this.isResist = isResist;
	}
	public String getIsAntennaPutOutside() {
		return isAntennaPutOutside;
	}
	public void setIsAntennaPutOutside(String isAntennaPutOutside) {
		this.isAntennaPutOutside = isAntennaPutOutside;
	}

	public String getIsPublicAdress() {
		return isPublicAdress;
	}
	public void setIsPublicAdress(String isPublicAdress) {
		this.isPublicAdress = isPublicAdress;
	}
	public String getIsScrapCrooked() {
		return isScrapCrooked;
	}
	public void setIsScrapCrooked(String isScrapCrooked) {
		this.isScrapCrooked = isScrapCrooked;
	}
	public String getAntennaSupportType() {
		return antennaSupportType;
	}
	public void setAntennaSupportType(String antennaSupportType) {
		this.antennaSupportType = antennaSupportType;
	}
	public String getSerrickHeight() {
		return serrickHeight;
	}
	public void setSerrickHeight(String serrickHeight) {
		this.serrickHeight = serrickHeight;
	}
	public String getAntennaModel() {
		return antennaModel;
	}
	public void setAntennaModel(String antennaModel) {
		this.antennaModel = antennaModel;
	}

	public String getAntennaManufacturer2() {
		return antennaManufacturer2;
	}
	public void setAntennaManufacturer2(String antennaManufacturer2) {
		this.antennaManufacturer2 = antennaManufacturer2;
	}
	public Double getLevelPowerAngle() {
		return levelPowerAngle;
	}
	public void setLevelPowerAngle(Double levelPowerAngle) {
		this.levelPowerAngle = levelPowerAngle;
	}
	public Double getVerticalPowerAngle() {
		return verticalPowerAngle;
	}
	public void setVerticalPowerAngle(Double verticalPowerAngle) {
		this.verticalPowerAngle = verticalPowerAngle;
	}
	public Double getInlayUnderAngle() {
		return inlayUnderAngle;
	}
	public void setInlayUnderAngle(Double inlayUnderAngle) {
		this.inlayUnderAngle = inlayUnderAngle;
	}
	public String getIsDoubleFrequencyAntenna() {
		return isDoubleFrequencyAntenna;
	}
	public void setIsDoubleFrequencyAntenna(String isDoubleFrequencyAntenna) {
		this.isDoubleFrequencyAntenna = isDoubleFrequencyAntenna;
	}
	public String getAntennaGain() {
		return antennaGain;
	}
	public void setAntennaGain(String antennaGain) {
		this.antennaGain = antennaGain;
	}
	public Integer getSearchStarNum() {
		return searchStarNum;
	}
	public void setSearchStarNum(Integer searchStarNum) {
		this.searchStarNum = searchStarNum;
	}

	public String getSurveyType() {
		return surveyType;
	}
	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}
	public String getGridArea() {
		return gridArea;
	}
	public void setGridArea(String gridArea) {
		this.gridArea = gridArea;
	}

	public String getElectromagneticDisturbIntension() {
		return electromagneticDisturbIntension;
	}
	public void setElectromagneticDisturbIntension(
			String electromagneticDisturbIntension) {
		this.electromagneticDisturbIntension = electromagneticDisturbIntension;
	}

	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Timestamp getSynchronizationTime() {
		return synchronizationTime;
	}
	public void setSynchronizationTime(Timestamp synchronizationTime) {
		this.synchronizationTime = synchronizationTime;
	}
	public String getTranslateState() {
		return translateState;
	}
	public void setTranslateState(String translateState) {
		this.translateState = translateState;
	}
	public String getSignalStrength() {
		return signalStrength;
	}
	public void setSignalStrength(String signalStrength) {
		this.signalStrength = signalStrength;
	}

	public Double getRollAngle() {
		return rollAngle;
	}
	public void setRollAngle(Double rollAngle) {
		this.rollAngle = rollAngle;
	}

	public Integer getInCount() {
		return inCount;
	}
	public void setInCount(Integer inCount) {
		this.inCount = inCount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getThresholdId() {
		return thresholdId;
	}
	public void setThresholdId(Long thresholdId) {
		this.thresholdId = thresholdId;
	}
	public Timestamp getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Timestamp getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Timestamp openTime) {
		this.openTime = openTime;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
}
