package com.example.socialMedia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialMedia.ResponseVo.FollowerRequestVo;
import com.example.socialMedia.ResponseVo.FollowerResponseVo;
import com.example.socialMedia.Services.IUserProfileService;
import com.example.socialMedia.entity.UserFollower;
import com.example.socialMedia.entity.UserProfile;
import com.example.socialMedia.exception.CustomException;
import com.example.socialMedia.utility.StaticSetup;

@RestController
@CrossOrigin
@RequestMapping("/socialMedia")
public class UserProfileController {
	
	Logger logger = LoggerFactory.getLogger(UserProfileController.class);
	public static String className = "UserProfileController";
	
	
	@Autowired
	IUserProfileService userProfileService;
	
	/**
	 * Follower added to the given user
	 * @param userId
	 * @param followerId
	 * @return
	 */
	@CrossOrigin
	@PostMapping("/followUser")
	public ResponseEntity<List<FollowerResponseVo>> followUser(@RequestBody FollowerRequestVo requestVo) {
		final String methodName = "followUser";
		
		logger.info("USER ID: " + requestVo.getUserId() + " FOLLOWER ID: " + requestVo.getFollowerId() + " " +
		methodName + " " + className);
		
		
		List<FollowerResponseVo> followerResponseVoList = new ArrayList<FollowerResponseVo>();
		
		requestVo.getFollowerId().stream().forEach(followerId -> {
			FollowerResponseVo responseVo= new FollowerResponseVo();
			try {
				UserFollower userFollower = new UserFollower();
				userFollower.setFollowerId(followerId);
				userFollower.setUserId(requestVo.getUserId());
				
				UserFollower addedFollower;
				
				addedFollower = userProfileService.addFollower(userFollower);
				
				responseVo.setMessage("Follower added successfully");
				responseVo.setStatus("SUCCESS");
				responseVo.setUserFollower(addedFollower);
				if(null != addedFollower) {
					logger.info("Follower Added, id: " + addedFollower.getId() +  " " + methodName + " " + className);
				}
					
			} catch (CustomException e) {
				logger.error("Error adding the follower: " + requestVo.getUserId() + " FOLLOWER ID: " + followerId + " error: "+ e.getMessage() +
						" " + methodName + " " + className);
				responseVo.setMessage(e.getMessage());
				responseVo.setStatus("ERROR");
			} catch(Exception ex) {
				responseVo.setMessage(ex.getMessage());
				responseVo.setStatus("ERROR");
			}
			followerResponseVoList.add(responseVo);
		});
		
		
		  return new ResponseEntity<List<FollowerResponseVo>>(followerResponseVoList, HttpStatus.OK);
		
		
	}
	
	/**
	 * Follower removed for a given user
	 * @param userId
	 * @param followerId
	 * @return
	 */
	@PostMapping("/unfollowUser")
	public ResponseEntity<List<FollowerResponseVo>> unfollowUser(@RequestBody FollowerRequestVo requestVo) {
		
		final String methodName = "unfollowUser";
		
		logger.info("USER ID: " + requestVo.getUserId() + " FOLLOWER ID: " + requestVo.getFollowerId() + " " 
		+ methodName + " " + className);
		
		List<FollowerResponseVo> followerResponseVoList = new ArrayList<FollowerResponseVo>();
		requestVo.getFollowerId().stream().forEach(followerId -> {
			FollowerResponseVo responseVo= new FollowerResponseVo();
			try {
				UserFollower userFollower = new UserFollower();
				userFollower.setFollowerId(followerId);
				userFollower.setUserId(requestVo.getUserId());
				
				UserFollower removedFollower = userProfileService.removeFollower(userFollower);
				responseVo.setMessage("Follower removed successfully");
				responseVo.setStatus("SUCCESS");
				responseVo.setUserFollower(removedFollower);
				if(null != removedFollower)
					logger.info("Follower Removed, id: " + removedFollower.getId() +  " " + methodName + 
							" " + className);
			} catch (CustomException e) {
				logger.error("Error removing the follower: " + requestVo.getUserId() + " FOLLOWER ID: " + followerId + " error: "+ e.getMessage() +
						" " + methodName + " " + className);
				responseVo.setMessage(e.getMessage());
				responseVo.setStatus("ERROR");
			} catch (Exception e) {
				responseVo.setMessage(e.getMessage());
				responseVo.setStatus("ERROR");
			}
			followerResponseVoList.add(responseVo);
		});
		
		
		  return new ResponseEntity<List<FollowerResponseVo>>(followerResponseVoList, HttpStatus.OK);
		
		
	}
	
	/**
	 * Display all the enrolled user
	 * @return
	 */
	@GetMapping("/displayAllUser")
	public List<UserProfile> displayAllUser() 
	{
		return StaticSetup.userProfileList;
	}
	
	/**
	 * Display all the follower mapping
	 * @return
	 */
	@GetMapping("/displayFollowerMapping")
	public List<UserFollower> displayFollowerMapping() 
	{
		return StaticSetup.userFollowerList;
	}
}
