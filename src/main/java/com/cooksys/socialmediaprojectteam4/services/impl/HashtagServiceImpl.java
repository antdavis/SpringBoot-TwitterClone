package com.cooksys.socialmediaprojectteam4.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.dtos.HashtagDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.entities.Hashtag;
import com.cooksys.socialmediaprojectteam4.entities.Tweet;
import com.cooksys.socialmediaprojectteam4.exceptions.NotFoundException;
import com.cooksys.socialmediaprojectteam4.mappers.HashtagMapper;
import com.cooksys.socialmediaprojectteam4.mappers.TweetMapper;
import com.cooksys.socialmediaprojectteam4.repositories.HashtagRepository;
import com.cooksys.socialmediaprojectteam4.repositories.TweetRepository;
import com.cooksys.socialmediaprojectteam4.services.HashtagService;
import com.cooksys.socialmediaprojectteam4.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
	
    private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;
    private final ValidateService validateService;
    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final TweetServiceImpl tweetServiceImpl;
    
    
    public Hashtag validateHashtag(String label) {
        if (!validateService.hashtagExists(label)) {
            throw new NotFoundException("The hashtag #" + label + " does not exist.");
        }
        return hashtagRepository.findByLabel(label).get();
    }
    
  @Override
  public List<HashtagDto> getAllHashtags() {
      return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
  }

  @Override
  public List<TweetResponseDto> getTweetsWithHashtag(String label) {
//	  Check if label is valid
      validateHashtag(label);
      List<Tweet> tweetsToReturn = new ArrayList<>();
//    Loops through non-deleted tweets, for each tweet retrieves list of hashtags
//    Then iterates through all hashtags, if label matches hashtag -> add to tweetsToReturn list
      for (Tweet tweet : tweetRepository.findAllByDeletedFalse()) {
      	for (HashtagDto hashtagdto : tweetServiceImpl.getTweetHashtags(tweet.getId())) {
      		if (hashtagdto.getLabel().equals(label)) {
      			tweetsToReturn.add(tweet);
      		}
      	}
      }
      return tweetMapper.entitiesToDtos(tweetsToReturn);
  }
 }

