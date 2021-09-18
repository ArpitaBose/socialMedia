# SocialMedia
# About The Project
Design a simple Social Media application(Maven Project). 

Users should be able to: 

1.       create new posts

2.       follow/unfollow another user

3.       view the 20 most recent posts in the user's news feed.

Your design should support the following methods:

createPost(userId, postId, content): Compose a new post.

getNewsFeed(userId): Retrieve the 20 most recent post ids in the user's news feed. Each item in the news feed must be posted either by one of the user’s followees or by the user herself. Posts must be ordered by posting time starting from the most recent one.

follow(followerId, followeeId): Follower follows a followee.

unfollow(followerId, followeeId): Follower unfollows a followee.

# Build With
Maven

# Getting Started
**Pre-requisite**
Java8
Eclipse with Maven

**Installation**
Clone the code from https://github.com/ArpitaBose/socialMedia

Import the project in eclipse
go to pom.xml
mvn clean install

**Usage**
Application running on port 8088
Hit http://localhost:8088/swagger-ui.html
(this will give all the api deatils and Beans details)

**Sample API**
1. URL :- http://localhost:8088/socialMedia/followUser
request Param 
userId: 5
followerId : 6

2. URL: - http://localhost:8088/socialMedia/unfollowUser
request Param 
userId: 3
followerId : 1

3. URL:- http://localhost:8088/socialMedia/savePost
request Param
userId : 5
content : "Hello"

4. URL:- http://localhost:8088/socialMedia/getFeeds
request Param
userId: 5

# Contact
Email id :- arpita2121991bose@gmail.com

