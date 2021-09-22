package com.example.socialMedia.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.socialMedia.ResponseVo.PostRequestVO;
import com.example.socialMedia.entity.UserPost;
import com.example.socialMedia.exception.CustomException;

@Service
public interface IUserPostService {

	List<UserPost> savePost(PostRequestVO posts) throws CustomException;

	List<UserPost> retrieveFeeds(int userId) throws CustomException;

	
}
