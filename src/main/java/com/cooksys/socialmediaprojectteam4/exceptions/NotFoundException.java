package com.cooksys.socialmediaprojectteam4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException {

  /**
  * 
  */
  private static final long serialVersionUID = 4981323902375687870L;

  private String message;

}
