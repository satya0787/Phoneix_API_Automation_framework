package com.pojos;

public class Problems {

	public Problems(int id, String remark) {
		super();
		this.id = id;
		this.remark = remark;
	}

	private int id;
	private String remark;

	// Added getters and setters so Jackson can serialize/deserialize this POJO
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}