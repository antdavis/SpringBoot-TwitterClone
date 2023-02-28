package com.cooksys.socialmediaprojectteam4.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.socialmediaprojectteam4.dtos.TweetRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.entities.Tweet;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {
	
    TweetResponseDto entityToDto(Tweet tweet);

  List<TweetResponseDto> entitiesToDtos(List<Tweet> tweets);

	Tweet requestDtoToEntity(TweetRequestDto tweetRequestDto);

	Tweet responseDtoToEntity(TweetResponseDto createTweet);

}
