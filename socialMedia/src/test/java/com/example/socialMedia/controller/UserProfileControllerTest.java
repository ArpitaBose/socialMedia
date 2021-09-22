package com.example.socialMedia.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.socialMedia.ResponseVo.FollowerRequestVo;
import com.example.socialMedia.ResponseVo.FollowerResponseVo;
import com.example.socialMedia.Services.IUserProfileService;
import com.example.socialMedia.Services.UserProfileImpl;
import com.example.socialMedia.entity.UserFollower;
import com.example.socialMedia.exception.CustomException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
//@WebMvcTest(UserProfileController.class)
public class UserProfileControllerTest {
	
	@Autowired
	WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@InjectMocks
	UserProfileController userProfileController;
	
	@Mock
	IUserProfileService userProfileServiceMock;
	
	@BeforeEach
	public   void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	
	@Test
	public void testFollowUser() throws CustomException, Exception {
		
		when(userProfileServiceMock.addFollower(Mockito.any(UserFollower.class))).thenReturn(Mockito.any(UserFollower.class));
		FollowerRequestVo reqVO= new FollowerRequestVo();
		reqVO.setUserId(5);
		List<Integer> list = new ArrayList<Integer>();
		list.add(6);
		list.add(7);
		reqVO.setFollowerId(list);
		ResponseEntity<List<FollowerResponseVo>> vo = userProfileController.followUser(reqVO);
		assertEquals(HttpStatus.OK, vo.getStatusCode());
		assertEquals(2, vo.getBody().size());
		
	}
	
	@Test()
	public void testNegativeFollowUser() throws CustomException, Exception {
		
		
		CustomException c = new CustomException("User is already following");
		
		when(userProfileServiceMock.addFollower(Mockito.any(UserFollower.class)))
		.thenThrow(new CustomException("User is already following"));
		
		FollowerRequestVo reqVO= new FollowerRequestVo();
		reqVO.setUserId(5);
		List<Integer> list = new ArrayList<Integer>();
		list.add(6);
		list.add(8);
		reqVO.setFollowerId(list);
		ResponseEntity<List<FollowerResponseVo>> vo = userProfileController.followUser(reqVO);
		
		assertEquals("User is already following", vo.getBody().get(0).getMessage());
		
	}
	
	@Test
	public void testUnfollowUser() throws CustomException, Exception {
		
		when(userProfileServiceMock.removeFollower(Mockito.any(UserFollower.class))).thenReturn(Mockito.any(UserFollower.class));
		FollowerRequestVo reqVO= new FollowerRequestVo();
		reqVO.setUserId(5);
		List<Integer> list = new ArrayList<Integer>();
		list.add(6);
		reqVO.setFollowerId(list);
		ResponseEntity<List<FollowerResponseVo>> vo = userProfileController.unfollowUser(reqVO);
		assertEquals(HttpStatus.OK, vo.getStatusCode());
		assertEquals("Follower removed successfully", vo.getBody().get(0).getMessage());
		
	}
	
	@Test()
	public void testNegativeUnfollowUser() throws CustomException, Exception {
		
		
		CustomException c = new CustomException("User is already following");
		
		when(userProfileServiceMock.removeFollower(Mockito.any(UserFollower.class)))
		.thenThrow(new CustomException("User is not Following, so cannot be removed"));
		
		FollowerRequestVo reqVO= new FollowerRequestVo();
		reqVO.setUserId(5);
		List<Integer> list = new ArrayList<Integer>();
		list.add(6);
		reqVO.setFollowerId(list);
		ResponseEntity<List<FollowerResponseVo>> vo = userProfileController.unfollowUser(reqVO);
		
		assertEquals("User is not Following, so cannot be removed", vo.getBody().get(0).getMessage());
		
	}
	
	
	
	
	

}
