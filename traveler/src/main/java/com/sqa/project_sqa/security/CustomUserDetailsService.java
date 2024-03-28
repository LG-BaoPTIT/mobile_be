package com.sqa.project_sqa.security;


import com.sqa.project_sqa.entities.User;
import com.sqa.project_sqa.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    private User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            this.userDetail = userRepository.findUserByUserName(username);
            if(Objects.isNull(userDetail)) {
                throw new UsernameNotFoundException("user not found");
            }
            return CustomUserDetails.mapUserToUserDetail(userDetail);

    }

    public User getUserDetail(){
        // userDetail.setPassword(null);
        return userDetail;
    }

}
