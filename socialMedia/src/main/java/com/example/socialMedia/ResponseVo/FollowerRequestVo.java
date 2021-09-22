package com.example.socialMedia.ResponseVo;

import java.util.List;

public class FollowerRequestVo {
	
	int userId;
	
	List<Integer> followerId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Integer> getFollowerId() {
		return followerId;
	}

	public void setFollowerId(List<Integer> followerId) {
		this.followerId = followerId;
	}

	public FollowerRequestVo(int userId, List<Integer> followerId) {
		super();
		this.userId = userId;
		this.followerId = followerId;
	}

	public FollowerRequestVo() {
		super();
	}
	
	

}
