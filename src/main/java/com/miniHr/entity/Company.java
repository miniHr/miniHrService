package com.miniHr.entity;

public class Company {

	private Integer id;
	private String name;
	private String job;
	private Integer position;
	private String type;
	private String image;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

	@Override
	public String toString() {
		return "id=[" + this.id + "],name=[" + this.name + "],job=[" + this.job + "],position=[" + this.position
				+ ",type=[" + this.type + "]";
	}
}
