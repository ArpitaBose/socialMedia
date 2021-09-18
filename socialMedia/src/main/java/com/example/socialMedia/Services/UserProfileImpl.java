package com.example.socialMedia.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.socialMedia.controller.UserProfileController;
import com.example.socialMedia.entity.UserFollower;
import com.example.socialMedia.exception.CustomException;
import com.example.socialMedia.repository.IUserProfileRepository;
import com.example.socialMedia.utility.StaticSetup;

@Service
public class UserProfileImpl implements IUserProfileService {
	
	Logger logger = LoggerFactory.getLogger(UserProfileController.class);
	public static String className = "UserProfileImpl";
	
	@Autowired
	IUserProfileRepository repo;

	/**
	 * Adding the valid follower to a valid user
	 */
	@Override
	public UserFollower addFollower(UserFollower userFollower) throws CustomException, Exception {
		final String methodName = "addFollower";
		
		//First check if the user is present in UserProfile Table
		boolean isUserPresent = StaticSetup.isUserPresent(userFollower.getUserId());
		logger.info("USER ID: " + userFollower.getUserId() + " is valid user " + " " + 
				methodName + " " + className);
		boolean isFollowerPresent = StaticSetup.isUserPresent(userFollower.getFollowerId());
		logger.info("USER ID: " + userFollower.getUserId() + " is valid user " + " " + 
				methodName + " " + className);
		
		if(isUserPresent && isFollowerPresent) {
			//Check if Already mapping is present
			if(!isUserFollowerMappingPresent(userFollower.getUserId(), userFollower.getFollowerId())) {
				logger.info("User and follower mapping not present, we can add "  + " " + 
						methodName + " " + className);
				//Mapping not present. We can do the mapping
				UserFollower updateUser = repo.save(userFollower);
				if(null != updateUser) {
					return updateUser;
				}
			} else {
				throw new CustomException("User is already following");
			}
		} else {
			throw new CustomException("User or Follower not enrolled");
		}
		return null;
	}

	private boolean isUserFollowerMappingPresent(int userId, int followerId) {
		if(!StaticSetup.userFollowerList.isEmpty()) {
			 return StaticSetup.userFollowerList.stream().anyMatch(p -> p.getUserId()==userId && p.getFollowerId()== followerId);
		} else {
			return false;
		}
		
		
	}
	
	
	/**
	 * Removing the follower from valid user if the mapping is already present
	 */
	@Override
	public UserFollower removeFollower(UserFollower userFollower) throws CustomException, Exception {
		final String methodName = "removeFollower";
		
		// First check if the user is present in UserProfile Table
		boolean isUserPresent = StaticSetup.isUserPresent(userFollower.getUserId());
		logger.info("USER ID: " + userFollower.getUserId() + " is valid user " + " " + 
				methodName + " " + className);
		boolean isFollowerPresent = StaticSetup.isUserPresent(userFollower.getFollowerId());
		logger.info("USER ID: " + userFollower.getUserId() + " is valid user " + " " + 
				methodName + " " + className);

		if (isUserPresent && isFollowerPresent) {
			// Check if Already mapping is present
			if (isUserFollowerMappingPresent(userFollower.getUserId(), userFollower.getFollowerId())) {
				logger.info("User and follower mapping  present, we can remove "  + " " + 
						methodName + " " + className);
				
				// Mapping not present. We can do the mapping
				UserFollower updateUser = repo.delete(userFollower);
				if (null != updateUser) {
					return updateUser;
				}
			} else {
				throw new CustomException("User is not Following, so cannot be removed");
			}
		} else {
			throw new CustomException("User or Follower not enrolled");
		}
		return null;
	}

	

}
