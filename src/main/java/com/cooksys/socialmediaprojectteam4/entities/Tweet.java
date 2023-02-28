package com.cooksys.socialmediaprojectteam4.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Tweet implements Comparable<Tweet> {

	@Id
	@GeneratedValue
	private Long id;
	    
	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;
	    
	@CreatedDate
	private Timestamp timePosted = Timestamp.valueOf(LocalDateTime.now());
	
	private boolean deleted;
		
	private String content; 
		
	@ManyToOne
	private Tweet inReplyTo;
		
	@OneToMany(mappedBy = "inReplyTo", 
					cascade = CascadeType.ALL)
	private List<Tweet> replies;
		
	@ManyToOne
	private Tweet repostOf;
	    
	@OneToMany(mappedBy = "repostOf", 
				cascade = CascadeType.ALL)
	private List<Tweet> reposts;
		
	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> mentionedUsers;
		
	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> likes;
		
	@ManyToMany(mappedBy = "tweets", 
				cascade = CascadeType.MERGE)
	private List<Hashtag> hashtags;


		
//	Sorts tweets based on their id values with the higher id values appearing first.
	   @Override
	   public int compareTo(Tweet o) {
			return (int) (o.getId() - this.id);
	    }	
	
}
