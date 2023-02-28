package com.cooksys.socialmediaprojectteam4.mappers;

import org.mapstruct.Mapper;

import com.cooksys.socialmediaprojectteam4.dtos.UserRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.entites.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User requestDtoToEntity(UserRequestDto userRequestDto);

  UserResponseDto entityToDto(User entity);
}
