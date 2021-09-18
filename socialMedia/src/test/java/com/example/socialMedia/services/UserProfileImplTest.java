package com.example.socialMedia.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialMedia.Services.UserProfileImpl;
import com.example.socialMedia.entity.UserFollower;
import com.example.socialMedia.exception.CustomException;
import com.example.socialMedia.repository.IUserProfileRepository;
import com.example.socialMedia.utility.StaticSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserProfileImplTest {
	
	@InjectMocks
	UserProfileImpl userProfileService;
	
	@Mock
	IUserProfileRepository repo;
	
	@BeforeEach
	public  void setUp() {
		MockitoAnnotations.openMocks(this);
		StaticSetup.populateUserProfileList();
	}
	
	
	@Test
	public void testAddFollower() throws CustomException, Exception {
		UserFollower updatedUserFollower = new UserFollower(10, 12, 15);
		
		when(repo.save(Mockito.any(UserFollower.class))).thenReturn(updatedUserFollower);
		UserFollower userFollower = new UserFollower(10, 12, 15);
		userProfileService.addFollower(userFollower);
		assertSame(updatedUserFollower.getFollowerId(), userFollower.getFollowerId());
	}
	
	@Test
	public void testNegativeAddFollowerNotEnrolled() throws CustomException, Exception {
		
		UserFollower userFollower = new UserFollower(10, 1200, 15);
		
		Throwable exception = assertThrows(CustomException.class, () -> userProfileService.addFollower(userFollower));
		
		assertEquals("User or Follower not enrolled", exception.getMessage());
		
	}
	
	@Test
	public void testNegativeAddFollower_UserAlreadyFollowing() throws CustomException, Exception {
		
		UserFollower userFollower = new UserFollower(10, 12, 15);
		StaticSetup.userFollowerList.add(userFollower);
		
		Throwable exception = assertThrows(CustomException.class, () -> userProfileService.addFollower(userFollower));
		
		assertEquals("User is already following", exception.getMessage());
		
	}
	
	@Test
	public void testRemoveFollower() throws CustomException, Exception {
		UserFollower updatedUserFollower = new UserFollower(10, 12, 15);
		
		when(repo.delete(Mockito.any(UserFollower.class))).thenReturn(updatedUserFollower);
		UserFollower userFollower = new UserFollower(10, 12, 15);
		userProfileService.removeFollower(userFollower);
		assertSame(updatedUserFollower.getFollowerId(), userFollower.getFollowerId());
	}
	
	

}
