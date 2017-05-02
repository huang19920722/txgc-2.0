package com.yp.sys.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * 版权所有：2016-重庆重邮汇测通讯技术有限公司
 * 项目名称：txgc   
 *
 * 类描述：图片信息
 * 类名称：com.yp.sys.entity.Image     
 * 创建人：nihui
 * 创建时间：2016-12-13 下午3:12:52   
 * 修改人：
 * 修改时间：   
 * 修改备注：   
 * @version   V1.0
 */
@Entity
@Table(name="t_cyhc_image_info")
public class Image implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long workerParameterId;
	private int picType;
	private String url;
	private int storagePeriod;
	private Timestamp uploadTime;
	private String recordStatus;
	private Long workOrderId;//工单ID

	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "com.yp.sys.util.IdGenerator")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="worker_parameter_id")
	public Long getWorkerParameterId() {
		return workerParameterId;
	}
	public void setWorkerParameterId(Long workerParameterId) {
		this.workerParameterId = workerParameterId;
	}
	@Column(name="pic_type")
	public int getPicType() {
		return picType;
	}
	public void setPicType(int picType) {
		this.picType = picType;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="storage_period")
	public int getStoragePeriod() {
		return storagePeriod;
	}
	public void setStoragePeriod(int storagePeriod) {
		this.storagePeriod = storagePeriod;
	}
	@Column(name="upload_time")
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Column(name="recordStatus")
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	
	@Column(name="work_order_id")
	public Long getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}
	

	
	
	
}
