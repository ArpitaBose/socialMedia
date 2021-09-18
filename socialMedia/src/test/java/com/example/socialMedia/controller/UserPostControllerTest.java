package com.example.socialMedia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.socialMedia.ResponseVo.FeedResponseVo;
import com.example.socialMedia.ResponseVo.SavePostResponseVo;
import com.example.socialMedia.Services.IUserPostService;
import com.example.socialMedia.entity.UserPost;
import com.example.socialMedia.exception.CustomException;
import com.example.socialMedia.utility.StaticSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserPostControllerTest {
	
	@InjectMocks
	UserPostController userPostController;
	
	@Mock
	IUserPostService userPostService;
	
	@BeforeEach
	public   void setUp() {
		MockitoAnnotations.openMocks(this);
		StaticSetup.populateUserProfileList();
	}
	
	@Test
	public void testSavePost() throws CustomException {
		
		when(userPostService.savePost(Mockito.any(UserPost.class))).thenReturn(Mockito.any(UserPost.class));
		
		ResponseEntity<SavePostResponseVo> responseEntity = userPostController.savePost(1, "TEst");
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Post Saved successfully", responseEntity.getBody().getMessage());
	}
	
	@Test
	public void testNegativeSavePost() throws CustomException {
		when(userPostService.savePost(Mockito.any(UserPost.class))).thenThrow(new CustomException("Not a valid user"));
		
		ResponseEntity<SavePostResponseVo> responseEntity = userPostController.savePost(1, "TEst");
		assertEquals("ERROR", responseEntity.getBody().getStatus());
		assertEquals("Not a valid user", responseEntity.getBody().getMessage());
	}
	
	@Test
	public void testGetFeeds() throws CustomException {
		//StaticSetup.populateUserPostList(10);
		when(userPostService.retrieveFeeds(Mockito.any(Integer.class))).thenReturn(Mockito.any(List.class));
		
		ResponseEntity<FeedResponseVo> responseEntity = userPostController.getFeeds(10);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void testNegativeGetFeeds() throws CustomException {
		when(userPostService.retrieveFeeds(Mockito.any(Integer.class))).thenThrow(new CustomException("Not a valid user"));
		
		ResponseEntity<FeedResponseVo> responseEntity = userPostController.getFeeds(10);
		assertEquals("ERROR", responseEntity.getBody().getStatus());
		assertEquals("Not a valid user", responseEntity.getBody().getMessage());
	}

}
