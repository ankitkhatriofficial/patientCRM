package com.example.PatientCRMxJWT.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * @author Ankit Khatri
 */
@Service
public class UserDetailsSeviceImpl implements UserDetailsService {
        @Override
        @Transactional
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            if(StringUtils.hasText(username) && username.equals("ANKIT")){
                return new User("ANKIT", "Test123", new ArrayList<>());
            }
            throw new RuntimeException("Invalid user..!");
        }
}
