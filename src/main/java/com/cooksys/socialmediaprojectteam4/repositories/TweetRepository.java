package com.cooksys.socialmediaprojectteam4.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaprojectteam4.entities.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
	
    List<Tweet> findAllByDeletedFalse();

    Optional<Tweet> findByIdAndDeletedFalse(Long id);

}
