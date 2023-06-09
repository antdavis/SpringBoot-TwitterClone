package com.cooksys.socialmediaprojectteam4.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.socialmediaprojectteam4.dtos.UserRequestDto;
import com.cooksys.socialmediaprojectteam4.dtos.UserResponseDto;
import com.cooksys.socialmediaprojectteam4.entities.User;

@Mapper(componentModel = "spring", uses = { CredentialsMapper.class, ProfileMapper.class })
public interface UserMapper {

  List<UserResponseDto> entitiesToDtos(List<User> entities);

  User userRequestDtoToEntity(UserRequestDto userRequestDto);

  @Mapping(target = "username", source = "credentials.username")
  UserResponseDto userEntityToDto(User entity);

}
