package com.example.socialMedia.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.socialMedia.entity.UserPost;

@Repository
public interface IUserPostRepository {
	
	UserPost save(UserPost userPost);

	List<UserPost> getFeeds(int userId, List<Integer> followersList);

}
