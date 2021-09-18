package com.example.socialMedia.ResponseVo;

import com.example.socialMedia.entity.UserPost;

public class SavePostResponseVo extends BaseResponse {
	
	UserPost userPost ;

	public UserPost getUserPost() {
		return userPost;
	}

	public void setUserPost(UserPost userPost) {
		this.userPost = userPost;
	}
	
	

}
