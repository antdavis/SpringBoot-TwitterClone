package com.cooksys.socialmediaprojectteam4.dtos;

import com.cooksys.socialmediaprojectteam4.entities.Credentials;
import com.cooksys.socialmediaprojectteam4.entities.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestDto {

  private Credentials credentials;

  private Profile profile;
}
