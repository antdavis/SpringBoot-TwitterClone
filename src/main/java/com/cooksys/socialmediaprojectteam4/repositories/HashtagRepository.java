package com.cooksys.socialmediaprojectteam4.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaprojectteam4.entities.Hashtag;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
	
    Optional<Hashtag> findByLabel(String label);
    
    @Query("SELECT DISTINCT h FROM Hashtag h JOIN FETCH h.tweets")
	List<Hashtag> findAllHashtags();

}
