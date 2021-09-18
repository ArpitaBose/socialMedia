package com.example.socialMedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

/**
 * 
 * @author Arpita
 * 
 * Social media app enable following features
 * Pre-requisite :- Users are already enrolled with the application
 * 1. Add follower to the given user
 * 2. Unfollow any user
 * 3. Post added by user
 * 4. Get the post feeds for a given user which contains post of user and its followers
 * 5. Display all the users, followers and the post at any given time
 *
 */
@SpringBootApplication
@OpenAPIDefinition
public class SocialMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
	}

}
