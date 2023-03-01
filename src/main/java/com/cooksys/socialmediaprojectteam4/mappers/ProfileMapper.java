package com.cooksys.socialmediaprojectteam4.mappers;

import org.mapstruct.Mapper;

import com.cooksys.socialmediaprojectteam4.dtos.ProfileDto;
import com.cooksys.socialmediaprojectteam4.entities.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

  Profile profileDtoToEntity(ProfileDto profileDto);
}
