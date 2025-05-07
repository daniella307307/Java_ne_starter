package com.example.demo.security;

import com.example.demo.enumerations.role.EUserStatus;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Transactional
    public UserDetails loadByUserId(Long id){
        User user = this.userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found with id: "+id));
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws com.example.demo.exceptions.BadRequestException {
        User user = userRepository.findUserByEmailOrUsername(username,username).orElseThrow(()->new UsernameNotFoundException("User not found with email or username: "+ s));
        if(!user.getStatus().equals(EUserStatus.ACTIVE)){
            throw new BadRequestException("User is not active");
        }
        return UserPrincipal.create(user);
    }
}
