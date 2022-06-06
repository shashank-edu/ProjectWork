package com.webapp.springbootweb.Users.controller;

import java.sql.Date;
import java.sql.Timestamp;

import com.webapp.springbootweb.Products.ExceptionalHandeling.ProductErrorResponse;
import com.webapp.springbootweb.Products.ExceptionalHandeling.ProductNotFoundException;
import com.webapp.springbootweb.Users.Models.JwtRequest;
import com.webapp.springbootweb.Users.Models.JwtResponse;
import com.webapp.springbootweb.Users.Service.CustomUserDetailService;
import com.webapp.springbootweb.helper.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import com.webapp.springbootweb.Users.Models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    // @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new ProductNotFoundException("User Not Found");
        }catch(BadCredentialsException e){
            e.printStackTrace();
            throw new ProductNotFoundException("Bad Credentials");
        }
        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());

        String token = jwtUtil.generateToken(userDetails);
        System.out.println("JWT " + token);

        // {"token":"value"}    

        return ResponseEntity.ok(new JwtResponse(token,jwtRequest.getUsername(),userDetails.getAuthorities()));
    }


    @ExceptionHandler
    public ProductErrorResponse handleException(ProductNotFoundException exe) {

        // create a ProductErrorResponce
        ProductErrorResponse error = new ProductErrorResponse();
        error.setMessage(exe.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp( new Timestamp(System.currentTimeMillis()));
        return error;
    }

}
