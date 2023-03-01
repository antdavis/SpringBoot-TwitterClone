package com.cooksys.socialmediaprojectteam4.mappers;

import org.mapstruct.Mapper;

import com.cooksys.socialmediaprojectteam4.dtos.CredentialsDto;
import com.cooksys.socialmediaprojectteam4.entities.Credentials;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

  Credentials credentialsDtoToEntity(CredentialsDto credentialsDto);
}
