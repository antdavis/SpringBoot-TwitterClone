package com.cooksys.socialmediaprojectteam4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 5202560430357152269L;

  private String message;

}
