package com.cooksys.socialmediaprojectteam4.dtos;

import java.sql.Timestamp;

import com.cooksys.socialmediaprojectteam4.entites.Credentials;
import com.cooksys.socialmediaprojectteam4.entites.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

  private Long id;

  private Credentials credentials;

  private Timestamp joined;

  private Profile profile;
}
