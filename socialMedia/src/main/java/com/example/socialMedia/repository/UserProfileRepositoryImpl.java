package com.example.socialMedia.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.socialMedia.entity.UserFollower;
import com.example.socialMedia.entity.UserPost;
import com.example.socialMedia.utility.StaticSetup;
@Repository
public class UserProfileRepositoryImpl implements IUserProfileRepository {

	@Override
	public UserFollower save(UserFollower userFollower) {
		userFollower.setId(StaticSetup.userFollowerList.size());
		StaticSetup.userFollowerList.add(userFollower);
		return userFollower;
	}

	@Override
	public UserFollower delete(UserFollower userFollower) {
		List<UserFollower> removeList = StaticSetup.userFollowerList.stream().filter(p -> p.getFollowerId()==userFollower.getFollowerId() && 
				p.getUserId() == userFollower.getUserId()).collect(Collectors.toList());
		if(null != removeList && !removeList.isEmpty()) {
			StaticSetup.userFollowerList.removeAll(removeList);
		}
		
		return userFollower;
	}

	@Override
	public List<Integer> getFollowers(int userId) {
		List<Integer> followerList = new ArrayList<Integer>();
				StaticSetup.userFollowerList.stream().filter(p -> p.getUserId() == userId)
				.mapToInt(p -> p.getFollowerId()).forEach(a -> followerList.add(a));
				
		return followerList;
	}

	



}
