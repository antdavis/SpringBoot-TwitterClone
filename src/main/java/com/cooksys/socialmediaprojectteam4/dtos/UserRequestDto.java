package com.cooksys.socialmediaprojectteam4.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestDto {

  private CredentialsDto credentials;

  private ProfileDto profile;
}
