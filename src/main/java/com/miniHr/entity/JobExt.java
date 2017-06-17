package com.miniHr.entity;

/**
 * Job信息
 *
 * Created by DELL on 2017/6/12.
 */
public class JobExt extends Job {


    private String companyName;

    private String image;
    
    private Integer boothId;
    
    private String information; 

	public Integer getBoothId() {
		return boothId;
	}

	public void setBoothId(Integer boothId) {
		this.boothId = boothId;
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
    
    public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}
