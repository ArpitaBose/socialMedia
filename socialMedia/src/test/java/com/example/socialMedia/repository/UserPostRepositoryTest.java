package com.example.socialMedia.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialMedia.entity.UserPost;
import com.example.socialMedia.utility.StaticSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserPostRepositoryTest {
	
	@InjectMocks
	UserPostRepository userPostrepo;
	
	@BeforeEach
	public  void setUp() {
		MockitoAnnotations.openMocks(this);
		StaticSetup.populateUserProfileList();
	}
	
	@Test
	public void testSave() {
		UserPost userPost = new UserPost(0, "test", 10, Calendar.getInstance());
		userPostrepo.save(userPost);
		assertTrue(StaticSetup.userPostList.size() > 0);
	}
	
	@Test
	public void testGetFeeds() {
		StaticSetup.populateUserPostList(5);
		
		StaticSetup.populateUserFollowerList(5);
		
		List<Integer> followerList = new ArrayList<Integer>();
		StaticSetup.userFollowerList.stream().filter(p -> p.getUserId() == 5)
		.mapToInt(p -> p.getFollowerId()).forEach(a -> followerList.add(a));
		
		List<UserPost> feedList = userPostrepo.getFeeds(5, followerList);
		assertTrue(feedList.size() == 20);
		
	}

}
