package com.cooksys.socialmediaprojectteam4.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.dtos.ProfileDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.entities.User;
import com.cooksys.socialmediaprojectteam4.exceptions.BadRequestException;
import com.cooksys.socialmediaprojectteam4.exceptions.NotAuthorizedException;

import com.cooksys.socialmediaprojectteam4.exceptions.NotFoundException;
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

  // get the user from db if exists
  public User getUser(String username) {
    Optional<User> optionalUser = userRepository.findByUsername(username);
    if (optionalUser.isEmpty())
      throw new NotFoundException("User doesn't exist.");
  
  
  
  
  public User getUserByCredentials(CredentialsDto credentialsDto) {
	return getUserByUsernameReturnUserEntity(credentialsDto.getUsername());
	}

  public User getUserByUsernameReturnUserEntity(String username) {
	Optional<User> optionalUser = userRepository.findByCredentialsUsername(username);
	if (optionalUser.isEmpty() || optionalUser.get().isDeleted()) {
		throw new NotFoundException("User with username: " + username + "not found");
	}
		return optionalUser.get();
	} 
  
  
  // Get all active (non-deleted) users as an array
  @Override
  public List<UserResponseDto> getAllUsers() {
    return userMapper.entitiesToDtos(userRepository.findAllByDeletedFalse());
  }

  @Override
  public UserResponseDto createUser(UserRequestDto userRequestDto) {
    User user = userMapper.userRequestDtoToEntity(userRequestDto);
//    user.setCredentials(user.getProfile());
//    user.setProfile(user.getProfile());


    return optionalUser.get();
  }

  // validate the credentials of the user
  public void validateCredentials(CredentialsDto credentialsDto, User userToDelete) {
    if (!userToDelete.getCredentials().getUsername().equalsIgnoreCase(credentialsDto.getUsername())) {
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
    if(userRepository.existsByCredentialsUsername(user.getCredentials().getUsername())) {
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

  // get us
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



}
