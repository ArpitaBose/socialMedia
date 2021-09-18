package com.example.socialMedia.ResponseVo;

import com.example.socialMedia.entity.UserFollower;

public class FollowerResponseVo extends BaseResponse{
	
	UserFollower userFollower;

	public UserFollower getUserFollower() {
		return userFollower;
	}

	public void setUserFollower(UserFollower userFollower) {
		this.userFollower = userFollower;
	}
	
	

}
