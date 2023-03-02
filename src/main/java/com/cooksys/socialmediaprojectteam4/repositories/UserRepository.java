package com.cooksys.socialmediaprojectteam4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaprojectteam4.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAllByDeletedFalse();

  boolean existsByCredentialsUsername(@Param("credentials.username") String username);

}
