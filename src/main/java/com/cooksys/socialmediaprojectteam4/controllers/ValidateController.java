package com.cooksys.socialmediaprojectteam4.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaprojectteam4.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

  private final ValidateService validateService;

  @GetMapping("/username/exists/@{username}")
  public boolean usernameExists(String username) {
    return validateService.usernameExists(username);
  }

  @GetMapping("/username/available/@{username}")
  public boolean usernameAvailable(String username) {
    return validateService.usernameAvailable(username);
  }
}
