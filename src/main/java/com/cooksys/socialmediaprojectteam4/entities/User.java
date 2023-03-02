package com.cooksys.socialmediaprojectteam4.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@Data
//@FilterDef(name = "deletedUserFilter", parameters = @ParamDef(name = "isDeleted", type = org.hibernate.type.descriptor.java.BooleanJavaType.class))
//@Filter(name = "deletedUserFilter", condition = "deleted = :isDeleted")
public class User {

  @Id
  @GeneratedValue
  private Long id;

  @Embedded
  private Credentials credentials;

  @CreationTimestamp
  private Timestamp joined;

//  private boolean deleted = Boolean.FALSE;
  private boolean deleted = Boolean.FALSE;

  @Embedded
  private Profile profile;

  // setting up relations with Tweets FK author
  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "author")
  private List<Tweet> tweets = new ArrayList<>();

  // setting up the relationships for followers_following
  @EqualsAndHashCode.Exclude
  @ManyToMany
  @JoinTable(name = "followers_following", joinColumns = @JoinColumn(name = "follower_id"), inverseJoinColumns = @JoinColumn(name = "following_id"))
  private Set<User> follower;

  @EqualsAndHashCode.Exclude
  @ManyToMany(mappedBy = "follower")
  private Set<User> following;

  // setting up the relationship with Tweet for user_likes
  @EqualsAndHashCode.Exclude
  @ManyToMany
  @JoinTable(name = "user_likes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tweet_id"))
  private Set<Tweet> userLikes;

  // setting up the relationship with Tweet for user_mentions
  @EqualsAndHashCode.Exclude
  @ManyToMany
  @JoinTable(name = "user_mentions", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tweet_id"))
  private List<Tweet> userMentions = new ArrayList<>();

}
