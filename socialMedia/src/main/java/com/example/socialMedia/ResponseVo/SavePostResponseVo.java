package com.example.socialMedia.ResponseVo;

import java.util.List;

import com.example.socialMedia.entity.UserPost;

public class SavePostResponseVo extends BaseResponse {
	
	List<UserPost> userPost ;

	public List<UserPost> getUserPost() {
		return userPost;
	}

	public void setUserPost(List<UserPost> userPost) {
		this.userPost = userPost;
	}
	
	

}
