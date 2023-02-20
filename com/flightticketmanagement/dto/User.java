package com.flightticketmanagement.dto;

public class User {
	private byte age;
	private String emailId;
	private long mobuleNumber;
	private String name;
	private String userId;
	private String possword;

	public String getPossword() {
		return possword;
	}

	public void setPossword(String possword) {
		this.possword = possword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getMobuleNumber() {
		return mobuleNumber;
	}

	public void setMobuleNumber(long mobuleNumber) {
		this.mobuleNumber = mobuleNumber;
	}

}
