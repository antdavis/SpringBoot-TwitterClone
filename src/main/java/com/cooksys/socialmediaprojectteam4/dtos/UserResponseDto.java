package com.cooksys.socialmediaprojectteam4.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

  private String username;

  private Timestamp joined;

  private ProfileDto profile;
}
