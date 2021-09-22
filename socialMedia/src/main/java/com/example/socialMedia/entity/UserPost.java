package com.example.socialMedia.entity;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserPost {
	
	int postId;
	
	String content;
	
	int createdUserId;
	
	Calendar createdTime;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "UserPost [postId=" + postId + ", content=" + content + ", createdUserId=" + createdUserId
				+ ", createdTime=" + createdTime + "]";
	}

	public UserPost() {
		super();
	}

	public UserPost(int postId, String content, int createdUserId, Calendar createdTime) {
		super();
		this.postId = postId;
		this.content = content;
		this.createdUserId = createdUserId;
		this.createdTime = createdTime;
	}
	
	
	
	

}
