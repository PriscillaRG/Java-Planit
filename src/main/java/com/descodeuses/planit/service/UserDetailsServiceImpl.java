package com.descodeuses.planit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.entity.UserEntity;
import com.descodeuses.planit.repository.UserRepository;

@Service
//interface UserDetailsService de Spring Security: oblige à implémenter la méthode loadUserByUsername(String username) pr authentification
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired // Injection automatique du Bean UserRepo
    private UserRepository repository;

    @Override
    // méthode d'implementation de UserDetailsService, appelé automatiquement par Spring Security pdt tentative connexion
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException { // paramètre username
        UserEntity user = repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("user not found") );

        return new User(  // User entité de spring security pas UserEntity
            user.getUsername(), 
            user.getPassword(), 
            List.of(new SimpleGrantedAuthority(user.getRole()))); 
    }

}