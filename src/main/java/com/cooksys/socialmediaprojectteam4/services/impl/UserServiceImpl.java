package com.cooksys.socialmediaprojectteam4.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.ProfileDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.entities.User;
import com.cooksys.socialmediaprojectteam4.mappers.UserMapper;
import com.cooksys.socialmediaprojectteam4.repositories.UserRepository;
import com.cooksys.socialmediaprojectteam4.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;
//  private final CredentialsMapper credentialsMapper;

  @Override
  public List<UserResponseDto> getAllUsers() {
    return userMapper.entitiesToDtos(userRepository.findAllByDeletedFalse());
  }

  @Override
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    User user = userMapper.userRequestDtoToEntity(userRequestDto);
//    user.setCredentials(user.getProfile());
//    user.setProfile(user.getProfile());

    return userMapper.userEntityToDto(userRepository.saveAndFlush(user));
  }

  @Override
  public UserResponseDto getUserByUsername(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserResponseDto updateUserProfile(CredentialsDto credentialsDto, ProfileDto profileDto) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserResponseDto deleteUser(CredentialsDto credentialsDto) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<UserResponseDto> followUser(String userName, CredentialsDto credentialsDto) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public Optional<UserResponseDto> unfollowUser(String userName, CredentialsDto credentialsDto) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public TweetResponseDto userFeed(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TweetResponseDto getAllTweetsByUser(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TweetResponseDto getAllTweetsMentionUser(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<UserResponseDto> getUserFollowers(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<UserResponseDto> getUserFollowings(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

}
