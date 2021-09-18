package com.example.socialMedia.Services;

import org.springframework.stereotype.Service;

import com.example.socialMedia.entity.UserFollower;
import com.example.socialMedia.exception.CustomException;

@Service
public interface IUserProfileService {

	UserFollower addFollower(UserFollower userFollower) throws CustomException, Exception;

	UserFollower removeFollower(UserFollower userFollower) throws CustomException, Exception;

}
