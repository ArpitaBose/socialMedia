package com.example.socialMedia.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.example.socialMedia.entity.UserFollower;
import com.example.socialMedia.entity.UserPost;
import com.example.socialMedia.entity.UserProfile;

@Component
public class StaticSetup {
	
	public static List<UserProfile> userProfileList = new ArrayList<UserProfile>();
	public static List<UserPost> userPostList = new ArrayList<UserPost>();
	public static List<UserFollower> userFollowerList = new ArrayList<UserFollower>();
	
	@PostConstruct
	public void setup() {
		//adding 20 users in list
		
		populateUserProfileList();
		System.out.println("User Profiles");
		//userProfileList.stream().forEach(System.out::println);
		//userProfileList.stream().forEach();
		
//		System.out.println();
//		System.out.println();
//		
//		System.out.println("Follower List");
//		populateUserFollowerList(5);
//		userFollowerList.stream().forEach(System.out::println);
//		
//		populateUserPostList(5);
		
	}
	
	public static boolean isUserPresent(int userId) {
		boolean isPresent = StaticSetup.userProfileList.stream().anyMatch(p -> p.getUserId() == userId);
		return isPresent;
	}
	
	public static void populateUserProfileList() {
		IntStream.iterate(1, operand-> operand+1).limit(20).forEach(operand -> userProfileList.add(new UserProfile(operand, "Test"+operand, "Test"+operand+"@gmail.com", 
				Calendar.getInstance(), Calendar.getInstance())));
	}
	
	public static void populateUserFollowerList(int userId) {
		
		IntStream.iterate(0, operand-> operand+1).filter(operand -> operand != userId).limit(20).forEach(operand -> 
		userFollowerList.add(new UserFollower(userFollowerList.size(), userId, operand)));
	}
	
	public static void populateUserPostList(int userId) {
		Random r = new Random();
		
		System.out.println("Follower List for User id : " + userId);
		List<Integer> followerList = new ArrayList<Integer>();
		 userFollowerList.stream().filter(p -> p.getUserId()== userId).
				mapToInt(p -> p.getFollowerId()).
				forEach(p -> followerList.add(p));
		followerList.stream().forEach(System.out::println);
		 
		System.out.println("Random post list for random user ");
		 //Random post added
		IntStream.iterate(1, operand-> operand+1).limit(20).forEach(operand -> userPostList.add(new UserPost(userPostList.size(),
				"Test"+operand, r.nextInt(15), Calendar.getInstance())));
		userPostList.stream().forEach(System.out::println);
		
		System.out.println("Post List after adding follower of user:  "+ userId);
		//Add post for user
		IntStream.range(0, followerList.size()).forEach(operand -> userPostList.add(new UserPost(userPostList.size(),
				"Post from"+followerList.get(operand), followerList.get(operand), Calendar.getInstance())));
		userPostList.stream().forEach(System.out::println);
		
	}

}
