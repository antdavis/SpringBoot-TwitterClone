package com.cooksys.socialmediaprojectteam4.services;

import java.util.List;

import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;

public interface UserService {

  List<UserResponseDto> getAllUsers();

  UserResponseDto createUser(UserRequestDto userRequestDto);

  UserResponseDto getUserByUsername(String userName);

  UserResponseDto updateUserProfile(UserRequestDto userRequestDto, String username);

  UserResponseDto deleteUser(CredentialsDto credentialsDto, String username);

  void followUser(CredentialsDto credentialsDto, String username);

  List<UserResponseDto> getUserFollowing(String username);

  List<UserResponseDto> getUserFollowers(String username);

  void unfollowUser(CredentialsDto credentialsDto, String username);

  List<TweetResponseDto> userFeed(String username);

  List<TweetResponseDto> getAllTweetsByUser(String username);

  List<TweetResponseDto> getAllTweetsMentionUser(String username);

}
