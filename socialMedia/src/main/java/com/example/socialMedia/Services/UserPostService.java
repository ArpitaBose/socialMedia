package com.example.socialMedia.Services;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialMedia.controller.UserProfileController;
import com.example.socialMedia.entity.UserPost;
import com.example.socialMedia.exception.CustomException;
import com.example.socialMedia.repository.IUserPostRepository;
import com.example.socialMedia.repository.IUserProfileRepository;
import com.example.socialMedia.utility.StaticSetup;

@Service
public class UserPostService implements IUserPostService {
	
	Logger logger = LoggerFactory.getLogger(UserProfileController.class);
	public static String className = "UserPostService";
	
	@Autowired
	IUserPostRepository postRepo;
	
	@Autowired
	IUserProfileRepository userProfileRepo;

	/**
	 * Saving the post if the user is a valid user
	 */
	@Override
	public UserPost savePost(UserPost userPost) throws CustomException {
		final String methodName = "savePost";
		
		if(StaticSetup.isUserPresent(userPost.getCreatedUserId())) {
			logger.info("USER ID: " + userPost.getCreatedUserId() + " is valid user " + " " + methodName + " " + className);
			userPost.setPostId(StaticSetup.userPostList.size());
			userPost.setCreatedTime(Calendar.getInstance());
			UserPost updatedPost =  postRepo.save(userPost);
			return updatedPost;
		} else {
			throw new CustomException("Not a valid user");
		}
		
	}

	
	/**
	 * Retrieving the post for the valid users
	 */
	@Override
	public List<UserPost> retrieveFeeds(int userId) throws CustomException {
		final String methodName = "retrieveFeeds";
		
		if(StaticSetup.isUserPresent(userId)) {
			logger.info("USER ID: " + userId + " is valid user " + " " + methodName + " " + className);
			
		//First pull all the followers
		List<Integer> followersList =  userProfileRepo.getFollowers(userId);
		logger.info("total follower of user:  " + userId + " is  " + followersList.size() + " " + methodName + " " + className);
		//Pull the feeds for user and its followers
		List<UserPost> userPost = postRepo.getFeeds(userId, followersList);
		return userPost;
		} else {
			throw new CustomException("Not a valid user");
		}
	}

}
