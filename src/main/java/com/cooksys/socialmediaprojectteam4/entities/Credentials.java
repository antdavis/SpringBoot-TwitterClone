package com.cooksys.socialmediaprojectteam4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Credentials {

  @Column(unique = true, nullable = false)
  private String username;

  private String password;

}
