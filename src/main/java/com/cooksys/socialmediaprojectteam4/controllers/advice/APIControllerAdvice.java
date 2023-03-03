package com.cooksys.socialmediaprojectteam4.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cooksys.socialmediaprojectteam4.dtos.ErrorDto;
import com.cooksys.socialmediaprojectteam4.exceptions.BadRequestException;
import com.cooksys.socialmediaprojectteam4.exceptions.NotAuthorizedException;
import com.cooksys.socialmediaprojectteam4.exceptions.NotFoundException;

@ControllerAdvice(basePackages = { "com.cooksys.socialmediaprojectteam4.controllers" })
@ResponseBody
public class APIControllerAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public ErrorDto handleBadRequestException(BadRequestException badRequestException) {
    return new ErrorDto(badRequestException.getMessage());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ErrorDto handleNotFoundException(NotFoundException notFoundException) {
    return new ErrorDto(notFoundException.getMessage());
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(NotAuthorizedException.class)
  public ErrorDto handleNotAusthorizedException(NotAuthorizedException notAusthorizedException) {
    return new ErrorDto(notAusthorizedException.getMessage());
  }
}
