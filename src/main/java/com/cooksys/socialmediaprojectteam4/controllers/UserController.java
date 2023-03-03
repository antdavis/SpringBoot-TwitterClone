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
import com.cooksys.socialmediaprojectteam4.dtos.TweetResponseDto;
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

  @PostMapping("/@{username}/follow")
  @ResponseStatus(HttpStatus.CREATED)
  public void followUser(@RequestBody CredentialsDto credentialsDto, @PathVariable String username) {
    userService.followUser(credentialsDto, username);
  }

  @PostMapping("/@{username}/unfollow")
  @ResponseStatus(HttpStatus.CREATED)
  public void unfollowUser(@RequestBody CredentialsDto credentialsDto, @PathVariable String username) {
    userService.unfollowUser(credentialsDto, username);
  }

  @GetMapping("/@{username}/feed")
  public List<TweetResponseDto> userFeed(@PathVariable String username) {
    return userService.userFeed(username);
  }

  @GetMapping("/@{username}/tweets")
  public List<TweetResponseDto> getAllTweetsByUser(String username) {
    return userService.getAllTweetsByUser(username);
  }

  @GetMapping("/@{username}/mentions")
  public List<TweetResponseDto> getAllTweetsMentionUser(String username) {
    return userService.getAllTweetsMentionUser(username);
  }

  @GetMapping("/@{username}/followers")
  public List<UserResponseDto> getUserFollowers(@PathVariable String username) {
    return userService.getUserFollowers(username);
  }

  @GetMapping("/@{username}/following")
  public List<UserResponseDto> getUserFollowings(@PathVariable String username) {
    return userService.getUserFollowing(username);
  }
}
