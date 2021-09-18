package com.example.socialMedia.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.socialMedia.entity.UserFollower;
@Repository
public interface IUserProfileRepository {

	UserFollower save(UserFollower userFollower);

	UserFollower delete(UserFollower userFollower);

	List<Integer> getFollowers(int userId);

	

}
