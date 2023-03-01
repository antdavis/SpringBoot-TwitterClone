package com.cooksys.socialmediaprojectteam4.services;

import java.util.List;
import java.util.Optional;

import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.ProfileDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;

public interface UserService {

  List<UserResponseDto> getAllUsers();

  UserResponseDto createUser(UserRequestDto userRequestDto);

  UserResponseDto getUserByUsername(String userName);

  UserResponseDto updateUserProfile(CredentialsDto credentialsDto, ProfileDto profileDto);

  UserResponseDto deleteUser(CredentialsDto credentialsDto);

  Optional<UserResponseDto> followUser(String userName, CredentialsDto credentialsDto);

  Optional<UserResponseDto> unfollowUser(String userName, CredentialsDto credentialsDto);

  TweetResponseDto userFeed(String userName);

  TweetResponseDto getAllTweetsByUser(String userName);

  TweetResponseDto getAllTweetsMentionUser(String userName);

  List<UserResponseDto> getUserFollowers(String userName);

  List<UserResponseDto> getUserFollowings(String userName);

}
