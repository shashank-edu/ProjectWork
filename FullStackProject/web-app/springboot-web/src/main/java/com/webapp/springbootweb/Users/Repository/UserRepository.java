package com.webapp.springbootweb.Users.Repository;

import com.webapp.springbootweb.Users.entity.User;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;

@Configurable
public interface UserRepository  extends JpaRepository<User,Integer>{

    User findById(int UserId);

    User findByUsername(String username);

    void deleteById(int UserId);

    // Object findById(String username);
    
}
