package com.cooksys.socialmediaprojectteam4.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaprojectteam4.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAllByDeletedFalse();

  boolean existsByCredentialsUsername(@Param("credentials.username") String username);
  
  Optional<User> findByCredentialsUsername(String username);

//  @Query(value = "SELECT u FROM #{#entityName} u WHERE u.credentials.username =?1 AND u.deleted = true")
//  boolean existsByUsernameAndDeletedTrue(String username);
  
  @Query(value = "SELECT u FROM #{#entityName} u WHERE u.credentials.username =?1 AND u.deleted = false")
  Optional<User> findByUsernameAndDeleted(String username);

//  User findByCredentialsUsernameAndDeletedFalse(@Param("credentials.username") String username);

}
