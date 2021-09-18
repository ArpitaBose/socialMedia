package com.example.socialMedia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialMedia.ResponseVo.FeedResponseVo;
import com.example.socialMedia.ResponseVo.SavePostResponseVo;
import com.example.socialMedia.Services.IUserPostService;
import com.example.socialMedia.entity.UserPost;
import com.example.socialMedia.exception.CustomException;
import com.example.socialMedia.utility.StaticSetup;

@RestController
@CrossOrigin
@RequestMapping("/socialMedia")
public class UserPostController {
	
	Logger logger = LoggerFactory.getLogger(UserPostController.class);
	public static String className = "UserPostController";
	
	@Autowired
	IUserPostService userPostService;
	
	/**
	 * Post added for a given user
	 * @param userId
	 * @param content
	 * @return
	 */
	@PostMapping("/savePost")
	public ResponseEntity<SavePostResponseVo> savePost(@RequestParam("userId")int userId, @RequestParam("content")String content) {
		final String methodName = "savePost";
		
		logger.info("USER ID: " + userId +  " " + methodName + " " + className);
		
		SavePostResponseVo responseVo = new SavePostResponseVo();
		try {
			UserPost userPost = new UserPost();
			userPost.setContent(content);
			userPost.setCreatedUserId(userId);
			UserPost updatePost = userPostService.savePost(userPost);
			
			responseVo.setUserPost(updatePost);
			responseVo.setStatus("SUCCESS");
			responseVo.setMessage("Post Saved successfully");
			if(null != updatePost)
				logger.info("Post added , id: " + updatePost.getPostId() +  " " + methodName + " " + className);
			else
				logger.info("Post added , id: " + -1 +  " " + methodName + " " + className);
		} catch (CustomException e) {
			logger.error("Error in saving the post: " + userId +  " error: "+ e.getMessage() +
					" " + methodName + " " + className);
			responseVo.setStatus("ERROR");
			responseVo.setMessage(e.getMessage());
		}
		
		return new ResponseEntity<SavePostResponseVo>(responseVo, HttpStatus.OK);
				
		
	}
	
	/**
	 * Recent feeds for given user which contains feeds of user and its followers
	 * @param userId
	 * @return
	 */
	@PostMapping("/getFeeds")
	public ResponseEntity<FeedResponseVo> getFeeds(@RequestParam("userId")int userId) {
		
		final String methodName = "getFeeds";
		logger.info("USER ID: " + userId +  " " + methodName + " " + className);
		
		FeedResponseVo responseVo = new FeedResponseVo();
		
		try {
			
			List<UserPost> userPostList = userPostService.retrieveFeeds(userId);
			responseVo.setFeeds(userPostList);
			responseVo.setMessage("Feeds generated");
			responseVo.setStatus("SUCCESS");
			if(null != userPostList)
				logger.info("Feeds received , size: " + userPostList.size() +  " " + methodName + " " + className);
			else
				logger.info("Feeds received , size: " + 0 +  " " + methodName + " " + className);
		} catch (CustomException e) {
			logger.error("Error on getting the feeds: " + userId +  " error: "+ e.getMessage() +
					" " + methodName + " " + className);
			responseVo.setMessage(e.getMessage());
			responseVo.setStatus("ERROR");
		}
		return new ResponseEntity<FeedResponseVo>(responseVo, HttpStatus.OK);
	}
	
	
	/**
	 * Display all the post
	 * @return
	 */
	@GetMapping("/displayUserPost")
	public List<UserPost> displayAllUser() 
	{
		return StaticSetup.userPostList;
	}

}
