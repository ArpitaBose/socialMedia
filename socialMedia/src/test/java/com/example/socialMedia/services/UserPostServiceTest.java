package com.example.socialMedia.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.example.socialMedia.ResponseVo.PostRequestVO;
import com.example.socialMedia.Services.UserPostService;
import com.example.socialMedia.entity.UserPost;
import com.example.socialMedia.exception.CustomException;
import com.example.socialMedia.repository.IUserPostRepository;
import com.example.socialMedia.repository.IUserProfileRepository;
import com.example.socialMedia.utility.StaticSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserPostServiceTest {
	
	@InjectMocks
	UserPostService userPostService;
	
	@Mock
	IUserPostRepository postRepo;
	
	@Mock
	IUserProfileRepository userProfileRepo;

	@BeforeEach
	public  void setUp() {
		MockitoAnnotations.openMocks(this);
		StaticSetup.populateUserProfileList();
	}
	
	@Test
	public void testSavePost() throws CustomException {
		UserPost updatedUserPost = new UserPost(0, "TEts", 12, Calendar.getInstance());
		
		PostRequestVO postReqVo = new PostRequestVO();
		postReqVo.setCreatedUserId(12);
		List<String> contentList = new ArrayList<String>();
		contentList.add("TEts");
		postReqVo.setContent(contentList);
		
		//UserPost userPost = new UserPost(0, "TEts", 12, Calendar.getInstance());
		List<UserPost> userPostList = new ArrayList<UserPost>();
		userPostList.add(updatedUserPost);
		
		when(postRepo.save(Mockito.any(UserPost.class))).thenReturn(updatedUserPost);
		
		userPostService.savePost(postReqVo);
		
		assertSame(postReqVo.getCreatedUserId(), updatedUserPost.getCreatedUserId());
	}
	
	@Test
	public void testRetrieveFeeds() throws CustomException {
		UserPost updatedUserPost = new UserPost(0, "TEts", 12, Calendar.getInstance());
		List<UserPost> userPost = new ArrayList<UserPost>();
		userPost.add(updatedUserPost);
		List<Integer> followersList = new ArrayList<Integer>();
		
		when(postRepo.getFeeds(Mockito.any(Integer.class), Mockito.any(List.class))).thenReturn(userPost);
		
		when(userProfileRepo.getFollowers(Mockito.any(Integer.class))).thenReturn(followersList);
		
		userPostService.retrieveFeeds(12);
		
		assertSame(updatedUserPost.getCreatedUserId(), userPost.get(0).getCreatedUserId());
	}
}
