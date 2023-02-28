package com.cooksys.socialmediaprojectteam4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaprojectteam4.entities.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
	
    Optional<Hashtag> findByLabel(String label);

}
