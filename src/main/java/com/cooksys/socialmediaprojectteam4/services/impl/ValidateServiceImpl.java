package com.cooksys.socialmediaprojectteam4.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaprojectteam4.repositories.UserRepository;
import com.cooksys.socialmediaprojectteam4.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

  private final UserRepository userRepository;

//  private final UserMapper userMapper;

  @Override
  public boolean usernameExists(String username) {
    return userRepository.existsByCredentialsUsername(username);
  }

  @Override
  public boolean usernameAvailable(String username) {
    return !userRepository.existsByCredentialsUsername(username);
  }

}
