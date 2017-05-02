package com.yp.sys.entity.workorder;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCyhcImageInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_cyhc_image_info", catalog = "txgc")
public class ImageInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer workerParameterId;
	private Integer picType;
	private String url;
	private Integer storagePeriod;
	private Timestamp uploadTime;

	// Constructors

	/** default constructor */
	public ImageInfo() {
	}

	/** minimal constructor */
	public ImageInfo(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ImageInfo(Integer id, Integer workerParameterId,
			Integer picType, String url, Integer storagePeriod,
			Timestamp uploadTime) {
		this.id = id;
		this.workerParameterId = workerParameterId;
		this.picType = picType;
		this.url = url;
		this.storagePeriod = storagePeriod;
		this.uploadTime = uploadTime;
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

	@Column(name = "worker_parameter_id")
	public Integer getWorkerParameterId() {
		return this.workerParameterId;
	}

	public void setWorkerParameterId(Integer workerParameterId) {
		this.workerParameterId = workerParameterId;
	}

	@Column(name = "pic_type")
	public Integer getPicType() {
		return this.picType;
	}

	public void setPicType(Integer picType) {
		this.picType = picType;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "storage_period")
	public Integer getStoragePeriod() {
		return this.storagePeriod;
	}

	public void setStoragePeriod(Integer storagePeriod) {
		this.storagePeriod = storagePeriod;
	}

	@Column(name = "upload_time", length = 19)
	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

}