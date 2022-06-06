package com.webapp.springbootweb.Users.Models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
    String token;
    String username;
    Collection<? extends GrantedAuthority> role;
    public JwtResponse() {
    }

    public JwtResponse(String token,String username,Collection<? extends GrantedAuthority> collection) {
        this.token = token;
        this.username=username;
        this.role=collection;
    }

    public Collection<? extends GrantedAuthority> getRole() {
        return role;
    }

    public void setRole(Collection<? extends GrantedAuthority> role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

   
}

// @GetMapping("/OAuth")
// public String OAuthmethod(Model model,OAuth2AuthenticationToken token) {
//     logger.info("Inside Oauth Method");
//     model.addAttribute("UserName", token.getPrincipal().getAttribute("login"));
//     model.addAttribute("UserRole", token.getPrincipal().getAuthorities());
//     System.out.println(token.getPrincipal());
//     return "home";
// }