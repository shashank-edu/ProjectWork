package com.webapp.springbootweb.Users.Service;

import com.webapp.springbootweb.Users.Models.CustomUserDetails;
import com.webapp.springbootweb.Users.Repository.UserRepository;
import com.webapp.springbootweb.Users.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User Not Found !!");
        }
        return new CustomUserDetails(user);
    }
    
}
