package com.cooksys.socialmediaprojectteam4.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.dtos.HashtagDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.services.HashtagService;
import com.cooksys.socialmediaprojectteam4.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {@Override
	public List<HashtagDto> getAllHashtags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TweetResponseDto> getTweetsWithHashtag(String label) {
		// TODO Auto-generated method stub
		return null;
	}

}
