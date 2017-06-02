package com.miniHr.entity;

import com.sun.xml.internal.ws.developer.Serialization;

import java.util.Date;

@Serialization
public class Company {

	private Integer id;
	private String companyName;
	private String image;
	/**
	 * 公司规模
	 */
	private Integer scale;
	private String address;
	private String welfare;
	/**
	 * 联系人姓名
	 */
	private String name;
	private String phone;
	private Integer boothId;
	private Date createDt;
	private String creater;
	private Date updateDt;
	private String updater;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWelfare() {
		return welfare;
	}

	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getBoothId() {
		return boothId;
	}

	public void setBoothId(Integer boothId) {
		this.boothId = boothId;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@Override
	public String toString() {
		return "Company{" +
				"id=" + id +
				", companyName='" + companyName + '\'' +
				", image='" + image + '\'' +
				", scale=" + scale +
				", address='" + address + '\'' +
				", welfare='" + welfare + '\'' +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", boothId=" + boothId +
				", createDt=" + createDt +
				", creater='" + creater + '\'' +
				", updateDt=" + updateDt +
				", updater='" + updater + '\'' +
				'}';
	}
}
