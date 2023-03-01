package com.cooksys.socialmediaprojectteam4.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet implements Comparable<Tweet> {

  @Id
  @GeneratedValue
  private Long id;

//setting up relations with User for FK author
//  @ManyToOne(cascade = CascadeType.ALL)
  @ManyToOne
  @JoinColumn(name = "author")
  private User author;

  @CreationTimestamp
  private Timestamp timePosted;

  private boolean deleted = false;

  private String content;

  // setting up one-many relationship for inReplyTo
//  @ManyToOne(optional = true, cascade = CascadeType.ALL)
  @ManyToOne(optional = true)
  private Tweet inReplyTo;

  @OneToMany(mappedBy = "inReplyTo")
  private List<Tweet> replies = new ArrayList<>();

  // setting up one-many relationship for repostOf
//  @ManyToOne(optional = true, cascade = CascadeType.ALL)
  @ManyToOne(optional = true)
  private Tweet repostOf;

  @OneToMany(mappedBy = "repostOf")
  private Set<Tweet> reposts;

  // setting up relationship with User for user_mentions
  @ManyToMany(mappedBy = "userMentions")
  private Set<User> mentionedUsers;

  // setting up relationship with User for user_likes
  @ManyToMany(mappedBy = "userLikes")
  private Set<User> likes;

  // setting up relationship with HastTag for tweet_hashtags
//  @ManyToMany(cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ManyToMany
  @JoinTable(name = "tweet_hashtags", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
  private Set<Hashtag> hashtags;

//	Sorts tweets based on their id values with the higher id values appearing first.
  @Override
  public int compareTo(Tweet o) {
    return (int) (o.getId() - this.id);
  }

}
