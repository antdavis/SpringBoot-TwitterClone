package com.cooksys.socialmediaprojectteam4.services;

import java.util.List;

import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;

public interface UserService {

  List<UserResponseDto> getAllUsers();

  UserResponseDto createUser(UserRequestDto userRequestDto);

  UserResponseDto getUserByUsername(String userName);

  UserResponseDto updateUserProfile(UserRequestDto userRequestDto, String username);

  UserResponseDto deleteUser(CredentialsDto credentialsDto, String username);

}
