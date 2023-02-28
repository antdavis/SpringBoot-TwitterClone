package com.cooksys.socialmediaprojectteam4.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.dtos.ContextDto;
import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.HashtagDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {@Override
	public List<TweetResponseDto> getAllTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto getTweetById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void likeTweet(Long id, CredentialsDto credentialsDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TweetResponseDto replyToTweet(Long id, TweetRequestDto tweetRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweetResponseDto retweet(Long id, CredentialsDto credentialsDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashtagDto> getTweetHashtags(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getTweetLikes(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContextDto getTweetContext(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getTweetReplies(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getTweetReposts(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserResponseDto> getTweetUsersMentioned(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
