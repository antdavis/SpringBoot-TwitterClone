package com.cooksys.socialmediaprojectteam4.services;

import java.util.List;

import com.cooksys.socialmediaprojectteam4.dtos.HashtagDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;

public interface HashtagService {
	List<HashtagDto> getAllHashtags();

    List<TweetResponseDto> getTweetsWithHashtag(String label);
}
