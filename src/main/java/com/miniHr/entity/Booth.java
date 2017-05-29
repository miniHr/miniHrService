package com.miniHr.entity;

public class Booth {
	/**展位id*/
	private int id;
	/**展位价格*/
	private long price;
	/**展位状态 1、未购买  2、购买中  3、已购买*/
	private String state;
	/**所属公司id*/
	private String companyId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}
