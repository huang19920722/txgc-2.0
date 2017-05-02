package com.yp.sys.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class CollectInfoBean {
	private Long id;
	private String communityScrambler;//小区识别码
	private Long workOrderId;//工单ID
	private Long terminalId;//采集终端ID
	private String workParameterNumber;//工参编号
	private String communityName;//小区名称
	private String name;//基站名称
	private Double longitude;//经度
	private Double latitude;//纬度
	private String spectrum;//频段
	private Double electronicUnderAngle;//电子下倾角
	private String orientationAngle;//方位角
	private String antennaHangHigh;//天线挂高
	private String altitude;//海拔高度
	private String trafficArea;//话务区域
	private String beautifyWay;//美化方式
	private String supportType;//支撑类型
	private String isPublicRru;//是否共RRU
	private String isPublicAntenna;//是否共天馈
	private String isResist;//是否阻挡
	private String isAntennaPutOutside;//是否天线外放
	private String isIdlePlatform;//是否有闲置平台
	private String isPublicAdress;//与其他运营商共址情况
	private String isScrapCrooked;//是否刮歪
	private String antennaSupportType;//天线支撑类型
	private String serrickHeight;//抱杆高度
	private String antennaModel;//天线型号
	private String antennaManufacturer;//天线厂家
	private Integer searchStarNum;//搜星颗数
	private String surveyType;//勘察类型
	private String gridArea;//网格区域
	private String recentReportedTerminal;//最近上报终端
	private String electromagneticDisturbIntension;//电磁干扰强度
	private String lac;//LAC
	private String ci;//ci
	private String reportTimeStart;//上报开始时间
	private String reportTimeEnd;//上报结束日期
	
	private String testTimeStr;//测试时间
	private String tac;//tac
	private String signalStrength;//RSRP（信号强度）
	private String rxqual;//RXQUAL（通话质量）
	private String pci;//PCI（物理小区标识）
	private String bcch;//BCCH（通用信息的信道）
	private String rsrq;//RSRQ（信号质量）
	private String bsic;//BSIC（基站识别码）
	private String sinr;//SINR（信号信噪比）
	private String feq;//FEQ
	private Double rollAngle;//横滚角
	private String terminalNum;//终端序列号
	private String opUser;//终端使用人
	private String remark;//备注说明
	private String rsrp;//RSRP
	private String frequencyPoint;//频点
	private Double mechanicalUnderAngle;//机械下倾角
	private String transArea;//trans_area
	private Long orgId;//org_id 机构id(运营商id)
	private String networkStandard;//network_standard
	private Double towerHeight;//tower_height
	
	private String explainState;//订单审核状态
	
	private String explainRemark;//人工审核备注
	

	
	
	public String getExplainRemark() {
		return explainRemark;
	}
	public void setExplainRemark(String explainRemark) {
		this.explainRemark = explainRemark;
	}
	public String getExplainState() {
		return explainState;
	}
	public void setExplainState(String explainState) {
		this.explainState = explainState;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCommunityScrambler() {
		return communityScrambler;
	}
	public void setCommunityScrambler(String communityScrambler) {
		this.communityScrambler = communityScrambler;
	}
	public Long getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}
	public Long getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}
	public String getWorkParameterNumber() {
		return workParameterNumber;
	}
	public void setWorkParameterNumber(String workParameterNumber) {
		this.workParameterNumber = workParameterNumber;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getSpectrum() {
		return spectrum;
	}
	public void setSpectrum(String spectrum) {
		this.spectrum = spectrum;
	}
	public Double getElectronicUnderAngle() {
		return electronicUnderAngle;
	}
	public void setElectronicUnderAngle(Double electronicUnderAngle) {
		this.electronicUnderAngle = electronicUnderAngle;
	}
	public String getOrientationAngle() {
		return orientationAngle;
	}
	public void setOrientationAngle(String orientationAngle) {
		this.orientationAngle = orientationAngle;
	}
	public String getAntennaHangHigh() {
		return antennaHangHigh;
	}
	public void setAntennaHangHigh(String antennaHangHigh) {
		this.antennaHangHigh = antennaHangHigh;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
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
	public String getSupportType() {
		return supportType;
	}
	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}
	public String getIsPublicRru() {
		return isPublicRru;
	}
	public void setIsPublicRru(String isPublicRru) {
		this.isPublicRru = isPublicRru;
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
	public String getIsIdlePlatform() {
		return isIdlePlatform;
	}
	public void setIsIdlePlatform(String isIdlePlatform) {
		this.isIdlePlatform = isIdlePlatform;
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
	public String getAntennaManufacturer() {
		return antennaManufacturer;
	}
	public void setAntennaManufacturer(String antennaManufacturer) {
		this.antennaManufacturer = antennaManufacturer;
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
	public String getRecentReportedTerminal() {
		return recentReportedTerminal;
	}
	public void setRecentReportedTerminal(String recentReportedTerminal) {
		this.recentReportedTerminal = recentReportedTerminal;
	}
	public String getElectromagneticDisturbIntension() {
		return electromagneticDisturbIntension;
	}
	public void setElectromagneticDisturbIntension(
			String electromagneticDisturbIntension) {
		this.electromagneticDisturbIntension = electromagneticDisturbIntension;
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

	public String getReportTimeStart() {
		return reportTimeStart;
	}
	public void setReportTimeStart(String reportTimeStart) {
		this.reportTimeStart = reportTimeStart;
	}
	public String getReportTimeEnd() {
		return reportTimeEnd;
	}
	public void setReportTimeEnd(String reportTimeEnd) {
		this.reportTimeEnd = reportTimeEnd;
	}
	public String getTestTimeStr() {
		return testTimeStr;
	}
	public void setTestTimeStr(String testTimeStr) {
		this.testTimeStr = testTimeStr;
	}
	public String getTac() {
		return tac;
	}
	public void setTac(String tac) {
		this.tac = tac;
	}
	public String getSignalStrength() {
		return signalStrength;
	}
	public void setSignalStrength(String signalStrength) {
		this.signalStrength = signalStrength;
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
	public Double getRollAngle() {
		return rollAngle;
	}
	public void setRollAngle(Double rollAngle) {
		this.rollAngle = rollAngle;
	}
	public String getTerminalNum() {
		return terminalNum;
	}
	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}
	public String getOpUser() {
		return opUser;
	}
	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRsrp() {
		return rsrp;
	}
	public void setRsrp(String rsrp) {
		this.rsrp = rsrp;
	}
	public String getFrequencyPoint() {
		return frequencyPoint;
	}
	public void setFrequencyPoint(String frequencyPoint) {
		this.frequencyPoint = frequencyPoint;
	}
	public Double getMechanicalUnderAngle() {
		return mechanicalUnderAngle;
	}
	public void setMechanicalUnderAngle(Double mechanicalUnderAngle) {
		this.mechanicalUnderAngle = mechanicalUnderAngle;
	}
	public String getTransArea() {
		return transArea;
	}
	public void setTransArea(String transArea) {
		this.transArea = transArea;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getNetworkStandard() {
		return networkStandard;
	}
	public void setNetworkStandard(String networkStandard) {
		this.networkStandard = networkStandard;
	}
	public Double getTowerHeight() {
		return towerHeight;
	}
	public void setTowerHeight(Double towerHeight) {
		this.towerHeight = towerHeight;
	}
	
	
}
