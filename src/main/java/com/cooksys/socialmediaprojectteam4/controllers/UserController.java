package com.cooksys.socialmediaprojectteam4.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<UserResponseDto> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
    return userService.createUser(userRequestDto);
  }

  @GetMapping("/@{username}")
  public UserResponseDto getUser(@PathVariable String username) {
    return userService.getUserByUsername(username);
  }

  @PatchMapping("/@{username}")
  public UserResponseDto updateUserProfile(@RequestBody UserRequestDto userRequestDto, @PathVariable String username) {
    return userService.updateUserProfile(userRequestDto, username);
  }

  @DeleteMapping("/@{username}")
  public UserResponseDto deleteUser(@RequestBody CredentialsDto credentialsDto, @PathVariable String username) {
    return userService.deleteUser(credentialsDto, username);
  }
//
//Optional<UserResponseDto> followUser(String userName, CredentialsDto credentialsDto);
//
//Optional<UserResponseDto> unfollowUser(String userName, CredentialsDto credentialsDto);
//
//TweetResponseDto userFeed(String userName);
//
//TweetResponseDto getAllTweetsByUser(String userName);
//
//TweetResponseDto getAllTweetsMentionUser(String userName);
//
//List<UserResponseDto> getUserFollowers(String userName);
//
//List<UserResponseDto> getUserFollowings(String userName);
}
