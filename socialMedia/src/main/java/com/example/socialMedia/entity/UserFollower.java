package com.example.socialMedia.entity;

import org.springframework.stereotype.Component;

@Component
public class UserFollower {
	
	int id;
	
	int userId;
	
	int followerId;
	
	

	public UserFollower() {
		super();
	}

	public UserFollower(int id, int userId, int followerId) {
		super();
		this.id = id;
		this.userId = userId;
		this.followerId = followerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	@Override
	public String toString() {
		return "UserFollower [id=" + id + ", userId=" + userId + ", followerId=" + followerId + "]";
	}
	
	

}
