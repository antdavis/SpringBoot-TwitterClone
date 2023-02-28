package com.cooksys.socialmediaprojectteam4.repositories;

import java.sql.Timestamp;

import com.cooksys.socialmediaprojectteam4.entites.Credentials;
import com.cooksys.socialmediaprojectteam4.entites.Profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRespository {

  private Long id;

  private Credentials credentials;

  private Timestamp joined;

  private Boolean deleted;

  private Profile profile;
}
