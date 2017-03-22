package com.miniHr.entity;

public class Company {

	private Integer id;
	private String name;
	private String job;
	private String position;
	private String type;

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "id=[" + this.id + "],name=[" + this.name + "],job=[" + this.job + "],position=[" + this.position
				+ ",type=[" + this.type + "]";
	}
}
