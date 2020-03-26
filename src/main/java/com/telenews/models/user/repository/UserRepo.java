package com.telenews.models.user.repository;

import com.telenews.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
    User findByEmail(String email);
}
