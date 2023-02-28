package com.cooksys.socialmediaprojectteam4.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDto {

  private String firstName;

  private String lastName;

  private String email;

  private String phone;
}
