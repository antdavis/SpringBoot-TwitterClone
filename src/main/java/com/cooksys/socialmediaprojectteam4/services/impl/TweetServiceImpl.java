package com.cooksys.socialmediaprojectteam4.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.dtos.ContextDto;
import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.HashtagDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.entities.Credentials;
import com.cooksys.socialmediaprojectteam4.entities.Tweet;
import com.cooksys.socialmediaprojectteam4.entities.User;
import com.cooksys.socialmediaprojectteam4.exceptions.BadRequestException;
import com.cooksys.socialmediaprojectteam4.exceptions.NotAuthorizedException;
import com.cooksys.socialmediaprojectteam4.exceptions.NotFoundException;
import com.cooksys.socialmediaprojectteam4.mappers.HashtagMapper;
import com.cooksys.socialmediaprojectteam4.mappers.TweetMapper;
import com.cooksys.socialmediaprojectteam4.mappers.UserMapper;
import com.cooksys.socialmediaprojectteam4.repositories.TweetRepository;
import com.cooksys.socialmediaprojectteam4.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
	
	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;
	private final HashtagMapper hashtagMapper;
	private final UserMapper userMapper;
	
	
	public Tweet getTweet(Long id) {
		if (tweetRepository.findByIdAndDeletedFalse(id).isEmpty()) {
			throw new NotFoundException("Tweet with ID " + id + " does not exist.");
		}
		return tweetRepository.findByIdAndDeletedFalse(id).get();
	}
	
	public void matchesCredentials(User databaseCredentials, CredentialsDto credentialsDto) {
		if (credentialsDto == null)
			throw new NotAuthorizedException("Credentials must be provided!");
		if (!databaseCredentials.getCredentials().getUsername().equalsIgnoreCase(credentialsDto.getUsername())
				|| !databaseCredentials.getCredentials().getPassword().equals(credentialsDto.getPassword())) {
			throw new NotAuthorizedException("Credentials do not match!");
		}
	}
	
	@Override
	public List<TweetResponseDto> getAllTweets() {
		List<Tweet> tweets = tweetRepository.findAllByDeletedFalse();
		Collections.sort(tweets);
		return tweetMapper.entitiesToDtos(tweets);
	}

	@Override
	public TweetResponseDto getTweetById(Long id) {
		return tweetMapper.entityToDto(getTweet(id));
	}

	@Override
	public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
//		Checks if tweet is empty
		if (tweetRequestDto.getContent() == null || tweetRequestDto.getContent().isEmpty() 
				|| tweetRequestDto.getContent().isBlank()) {
			throw new BadRequestException("Tweet cannot be empty!");
		}
//		Checks if username and password field is empty
		if (tweetRequestDto.getCredentials() == null) {
			throw new BadRequestException("Username and password is required!");
		}
		if (tweetRequestDto.getCredentials().getUsername() == null) {
			throw new BadRequestException("Username is required!");
		}
		if (tweetRequestDto.getCredentials().getPassword() == null) {
			throw new BadRequestException("Password is required!");
		}
//		Checks if username and password match
//		if () {
//			throw new NotAuthorizedException("Your credentials do not match.");
//		}
		
		return null;
	}

	@Override
	public TweetResponseDto deleteTweet(Long id, CredentialsDto credentialsDto) {
		matchesCredentials(getTweet(id).getAuthor(), credentialsDto);
		getTweet(id).setDeleted(true);
		return tweetMapper.entityToDto(tweetRepository.saveAndFlush(getTweet(id)));
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
		return hashtagMapper.entitiesToDtos(getTweet(id).getHashtags());
	}

	@Override
	public List<UserResponseDto> getTweetLikes(Long id) {
//		Get list of users who have liked the tweet
		List<User> likes = getTweet(id).getLikes();
//		If deleted remove user from list
		likes.removeIf(User::isDeleted);
		return userMapper.entitiesToDtos(likes);
	}

	@Override
	public ContextDto getTweetContext(Long id) {
		
//		Holds context info
		ContextDto context = new ContextDto();
//		Gets tweet and parent/child tweet
		Tweet inReplyTo = getTweet(id).getInReplyTo();
		List<Tweet> replies = getTweet(id).getReplies();
		List<Tweet> listOfInReplyTo = new ArrayList<>();
//		Add tweet's parent tweet to list and continues to add to list until no more parent tweets 
		while (inReplyTo != null) {
			listOfInReplyTo.add(inReplyTo);
			inReplyTo = inReplyTo.getInReplyTo();
		}
//		Removes deleted tweets
		listOfInReplyTo.removeIf(Tweet::isDeleted);
		replies.removeIf(Tweet::isDeleted);
//		Set mapped tweet as target, parents as before, and after as list
		context.setTarget(tweetMapper.entityToDto(getTweet(id)));
		context.setBefore(tweetMapper.entitiesToDtos(listOfInReplyTo));
		context.setAfter(tweetMapper.entitiesToDtos(replies));

		return context;
	}

	@Override
	public List<TweetResponseDto> getTweetReplies(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getTweetReposts(Long id) {
//		Get list of tweets that have been reposted
		List<Tweet> reposts = getTweet(id).getReposts();
//		Sorts list in order based on compareTo method created in Tweet entity class
		Collections.sort(reposts);
//		If deleted remove tweet from list
		reposts.removeIf(Tweet::isDeleted); 
		return tweetMapper.entitiesToDtos(reposts);
	}

	@Override
	public List<UserResponseDto> getTweetUsersMentioned(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
