package com.cooksys.socialmediaprojectteam4.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Credentials {

  @Column(unique = true)
  private String username;

  private String password;

}
