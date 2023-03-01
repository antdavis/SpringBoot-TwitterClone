package com.cooksys.socialmediaprojectteam4.dtos;

import java.sql.Timestamp;

import com.cooksys.socialmediaprojectteam4.entities.Credentials;
import com.cooksys.socialmediaprojectteam4.entities.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

  private Credentials credentials;

  private Timestamp joined;

  private Profile profile;
}
