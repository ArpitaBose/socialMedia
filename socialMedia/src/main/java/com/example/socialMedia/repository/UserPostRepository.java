package com.example.socialMedia.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.socialMedia.entity.UserPost;
import com.example.socialMedia.utility.StaticSetup;

@Repository
public class UserPostRepository implements IUserPostRepository{
	
	@Override
	public UserPost save(UserPost userPost) {
		StaticSetup.userPostList.add(userPost);
		return userPost;
	}

	@Override
	public List<UserPost> getFeeds(int userId, List<Integer> followersList) {
		//to get the latest, list is reversed
		 Collections.reverse(StaticSetup.userPostList);
		 List<UserPost> feedList  = new ArrayList<UserPost>();
		 
		feedList = StaticSetup.userPostList.stream().filter( p -> ((p.getCreatedUserId() == userId) ||
				 (null != followersList && !followersList.isEmpty() && followersList.contains(p.getCreatedUserId())))).limit(20)
				.collect(Collectors.toList());
		return feedList;
	}

}
