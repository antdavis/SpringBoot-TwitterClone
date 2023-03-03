package com.cooksys.socialmediaprojectteam4.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.ProfileDto;
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.entities.Tweet;
import com.cooksys.socialmediaprojectteam4.entities.User;
import com.cooksys.socialmediaprojectteam4.exceptions.BadRequestException;
import com.cooksys.socialmediaprojectteam4.exceptions.NotAuthorizedException;
import com.cooksys.socialmediaprojectteam4.exceptions.NotFoundException;
import com.cooksys.socialmediaprojectteam4.mappers.TweetMapper;
import com.cooksys.socialmediaprojectteam4.mappers.UserMapper;
import com.cooksys.socialmediaprojectteam4.repositories.UserRepository;
import com.cooksys.socialmediaprojectteam4.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;
  private final TweetMapper tweetMapper;
//  private final CredentialsMapper credentialsMapper;

  // get the user from db if exists
  public User getUser(String username) {
    Optional<User> optionalUser = userRepository.findByUsernameAndDeleted(username);
    if (optionalUser.isEmpty())
      throw new NotFoundException("User with username: " + username + " not found!");

    return optionalUser.get();
  }

  public User getUserByCredentials(CredentialsDto credentialsDto) {
    return getUserByUsernameReturnUserEntity(credentialsDto.getUsername());
  }

  //  QUESTIONG ON THE .ISDELTED()
  public User getUserByUsernameReturnUserEntity(String username) {
    Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
    if (optionalUser.isEmpty() || optionalUser.get().isDeleted()) {
      throw new NotFoundException("User with username: " + username + "not found");
    }
    return optionalUser.get();
  }

  // validate the credentials of the user
  public void validateCredentials(CredentialsDto credentialsDto, User userToDelete) {
    if (!userToDelete.getCredentials().getUsername().equalsIgnoreCase(credentialsDto.getUsername())
        || userToDelete.getCredentials().getPassword().equalsIgnoreCase(credentialsDto.getPassword())) {
      throw new NotAuthorizedException("Credentials do not match!");
    }
  }

  public void checkProfile(ProfileDto profileDto) {
    if (profileDto == null || profileDto.getEmail() == null)
      throw new BadRequestException("Profile missing!");
  }

  private void validateUserRequest(UserRequestDto userRequestDto) {
    if (userRequestDto == null)
      throw new BadRequestException("Invalid user request.");
    checkCredentials(userRequestDto.getCredentials());
    checkProfile(userRequestDto.getProfile());

  }

  // validate the credentials of the user
  public void checkCredentials(CredentialsDto credentialsDto) {
    if (credentialsDto == null || credentialsDto.getUsername() == null || credentialsDto.getPassword() == null)
      throw new BadRequestException("Credentials missing!");
  }

  
  // Get all active (non-deleted) users as an array
  @Override
  public List<UserResponseDto> getAllUsers() {
    return userMapper.entitiesToDtos(userRepository.findAllByDeletedFalse());
  }

  // create new user
  @Override
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    validateUserRequest(userRequestDto);
    User user = userMapper.userRequestDtoToEntity(userRequestDto);
    if (userRepository.existsByCredentialsUsername(user.getCredentials().getUsername())) {
      validateCredentials(userRequestDto.getCredentials(), user);
//      if(user.isDeleted()) {
      user.setDeleted(false);
//      }
//      else
      throw new NotAuthorizedException("Don't have authorization");
    }
//    user.setCredentials(user.getProfile());
//    user.setProfile(user.getProfile());

    return userMapper.userEntityToDto(userRepository.saveAndFlush(user));
  }

  
  // get user by username
  @Override
  public UserResponseDto getUserByUsername(String username) {
    User user = getUser(username);
    return userMapper.userEntityToDto(user);
  }

  @Override
  public UserResponseDto updateUserProfile(UserRequestDto userRequestDto, String username) {
    validateUserRequest(userRequestDto);
    User userToUpdate = userMapper.userRequestDtoToEntity(userRequestDto);

//    validateCredentials(credentialsDto, userToUpdate);
    userToUpdate.getCredentials().setUsername(username);
    return userMapper.userEntityToDto(userRepository.saveAndFlush(userToUpdate));
  }

  @Override
  public UserResponseDto deleteUser(CredentialsDto credentialsDto, String username) {
    checkCredentials(credentialsDto);
    User userToDelete = getUser(username);
    validateCredentials(credentialsDto, userToDelete);
    userToDelete.setDeleted(true);
    return userMapper.userEntityToDto(userRepository.saveAndFlush(userToDelete));
  }

  @Override
  public void followUser(CredentialsDto credentialsDto, String username) {
    checkCredentials(credentialsDto);
    User userFollower = getUser(credentialsDto.getUsername());
    validateCredentials(credentialsDto, userFollower);
    User userToFollow = getUser(username);
    if (userFollower.getFollower().contains(userToFollow))
      throw new NotAuthorizedException("Already following.");
    userFollower.getFollower().add(userToFollow);
    userRepository.saveAndFlush(userFollower);

  }

  @Override
  public void unfollowUser(CredentialsDto credentialsDto, String username) {
    checkCredentials(credentialsDto);
    User userFollower = getUser(credentialsDto.getUsername());
    validateCredentials(credentialsDto, userFollower);
    User userToUnfollow = getUser(username);
    if (userFollower.getFollower().contains(userToUnfollow))
      throw new NotAuthorizedException("Already following.");
    userFollower.getFollower().remove(userToUnfollow);
    userRepository.saveAndFlush(userFollower);

  }

  @Override
  public List<UserResponseDto> getUserFollowing(String username) {
    User user = getUser(username);
    if (user.getFollowing().isEmpty())
      throw new NotFoundException("Not following anyone.");
    return userMapper.entitiesToDtos(new ArrayList<User>(user.getFollowing()));
  }

  @Override
  public List<UserResponseDto> getUserFollowers(String username) {
    User user = getUser(username);
    if (user.getFollower().isEmpty())
      throw new NotFoundException("Not following anyone.");
    return userMapper.entitiesToDtos(new ArrayList<User>(user.getFollower()));
  }

  @Override
  public List<TweetResponseDto> userFeed(String username) {
    User user = getUser(username);
    List<Tweet> feed = new ArrayList<>();

    user.getTweets().forEach(t -> {
      feed.add(t);
      feed.addAll(t.getReplies());
      feed.addAll(new ArrayList<>(t.getReposts()));
    });

    for (User e : user.getFollowing()) {
      e.getTweets().forEach(t -> {
        feed.add(t);
        feed.addAll(t.getReplies());
        feed.addAll(new ArrayList<>(t.getReposts()));
      });

    }

    Collections.sort(feed);
    Collections.reverse(feed);
    return tweetMapper.entitiesToDtos(feed);
  }

  @Override
  public List<TweetResponseDto> getAllTweetsByUser(String username) {
    User user = getUser(username);
    List<Tweet> tweets = new ArrayList<>();

    user.getTweets().forEach(t -> {
      tweets.add(t);
      tweets.addAll(t.getReplies());
      tweets.addAll(new ArrayList<>(t.getReposts()));
    });

    Collections.sort(tweets);
    Collections.reverse(tweets);
    return tweetMapper.entitiesToDtos(tweets);
  }

  @Override
  public List<TweetResponseDto> getAllTweetsMentionUser(String username) {
    User user = getUser(username);
    List<Tweet> mentions = new ArrayList<>(user.getUserMentions());

    Collections.sort(mentions);
    Collections.reverse(mentions);
    return tweetMapper.entitiesToDtos(mentions);
  }

}
