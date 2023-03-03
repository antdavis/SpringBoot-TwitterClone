package com.cooksys.socialmediaprojectteam4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 8145756307600323853L;

  private String message;

}
