package com.cooksys.socialmediaprojectteam4.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.socialmediaprojectteam4.dtos.HashtagDto;
import com.cooksys.socialmediaprojectteam4.entities.Hashtag;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

  HashtagDto entityToDto(Hashtag hashtag);

  List<HashtagDto> entitiesToDtos(List<Hashtag> hashtags);

  Hashtag stringToEntity(String string);

}
