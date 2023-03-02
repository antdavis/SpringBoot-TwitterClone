package com.cooksys.socialmediaprojectteam4.services;

public interface ValidateService {

  boolean usernameExists(String username);

  boolean usernameAvailable(String username);

}
