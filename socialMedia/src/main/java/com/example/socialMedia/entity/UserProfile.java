package com.example.socialMedia.entity;

import java.util.Calendar;

import org.springframework.stereotype.Component;


@Component
public class UserProfile {

	int userId;
	
	String name;
	
	String emailId;
	
	Calendar createdDate;
	
	Calendar updatedDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public Calendar getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Calendar updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	

	public UserProfile() {
		super();
	}

	public UserProfile(int userId, String name, String emailId, Calendar createdDate, Calendar updatedDate) {
		super();
		this.userId = userId;
		this.name = name;
		this.emailId = emailId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", name=" + name + ", emailId=" + emailId + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
	
}
