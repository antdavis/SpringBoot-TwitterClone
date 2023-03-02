package com.cooksys.socialmediaprojectteam4.services;

import java.util.List;

import com.cooksys.socialmediaprojectteam4.dtos.ContextDto;
import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.HashtagDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;

public interface TweetService {

	List<TweetResponseDto> getAllTweets();
	
	TweetResponseDto getTweetById(Long id);
	
	TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);
	
	TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto);
	
    void likeTweet(Long id, CredentialsDto credentialsDto);
    
    TweetResponseDto replyToTweet(Long id, TweetRequestDto tweetRequestDto);
    
    TweetResponseDto retweet(Long id, CredentialsDto credentialsDto);
    
    List<HashtagDto> getTweetHashtags(Long id);
    
    List<UserResponseDto> getTweetLikes(Long id);
    
    ContextDto getTweetContext(Long id);
    
    List<TweetResponseDto> getTweetReplies(Long id);
    
    List<TweetResponseDto> getTweetReposts(Long id);
    
    List<UserResponseDto> getTweetUsersMentioned(Long id);

}
