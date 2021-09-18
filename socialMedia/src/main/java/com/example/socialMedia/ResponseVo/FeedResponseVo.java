package com.example.socialMedia.ResponseVo;

import java.util.List;

import com.example.socialMedia.entity.UserPost;

public class FeedResponseVo extends BaseResponse {

	List<UserPost> feeds;

	public List<UserPost> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<UserPost> feeds) {
		this.feeds = feeds;
	}
	
}
