package com.example.socialMedia.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialMedia.entity.UserFollower;
import com.example.socialMedia.utility.StaticSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserProfileRepositoryTest {
	
	@InjectMocks
	UserProfileRepositoryImpl userProfileRepository;
	
	@BeforeEach
	public  void setUp() {
		MockitoAnnotations.openMocks(this);
		StaticSetup.populateUserProfileList();
	}
	
	@Test
	public void testGetFollowers() {
		StaticSetup.userFollowerList.add(new UserFollower(0, 10, 15));
		List<Integer> followerList = userProfileRepository.getFollowers(10);
		assertTrue(followerList.size() > 0);
	}
	
	@Test
	public void testSave() {

		UserFollower userFollower = new UserFollower(0, 10, 12);
		userProfileRepository.save(userFollower);
		assertTrue(StaticSetup.userFollowerList.size() > 0);
	}
	
	@Test
	public void testDelete () {
		StaticSetup.userFollowerList =  new ArrayList<UserFollower>();
		StaticSetup.userFollowerList.add(new UserFollower(0, 10, 15));
		
		UserFollower followerToDelete = new  UserFollower(0, 10, 15);
		
		userProfileRepository.delete(followerToDelete);
		
		assertTrue(StaticSetup.userFollowerList.size() == 0);
		
	}
 
}
