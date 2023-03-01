package com.cooksys.socialmediaprojectteam4.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

  @Embedded
  private Credentials credentials;

  @CreationTimestamp
  private Timestamp joined;

  private boolean deleted = false;

  @Embedded
  private Profile profile;

  // setting up relations with Tweets FK author
  @OneToMany(mappedBy = "author")
  private List<Tweet> tweets = new ArrayList<>();

  // setting up the relationships for followers_following
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "followers_following")
  private Set<User> follower;

  @ManyToMany(mappedBy = "follower")
  private Set<User> following;

  // setting up the relationship with Tweet for user_likes
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_likes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tweet_id"))
  private Set<User> userLikes;

  // setting up the relationship with Tweet for user_mentions
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_mentions", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tweet_id"))
  private Set<User> userMentions;

}
