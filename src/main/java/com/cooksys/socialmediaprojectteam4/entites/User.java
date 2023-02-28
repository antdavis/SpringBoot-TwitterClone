package com.cooksys.socialmediaprojectteam4.entites;

import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Data
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToMany
  @JoinTable(name = "followers_following")
  private Set<User> followers;

  @ManyToMany(mappedBy = "followers")
  private Set<User> following;

  @Embedded
  private Credentials credentials;

  @CreationTimestamp
  private Timestamp joined;

  private Boolean deleted;

  @Embedded
  private Profile profile;

}
