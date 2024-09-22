package com.example.city_Taxi.security;

import com.example.city_Taxi.model.User;
import com.example.city_Taxi.service.UserService;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            // Fetch the ResponseMessage from the user service
            User user = userService.findUserByUsername(username);
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());

        } catch (Exception ex) {
            // Log the error and return a generic error response
            log.error("Unexpected error occurred while loading user", ex);
            return (UserDetails) new ResponseMessage(500, Alert.backendError, null); // 500 for server error
        }

    }
}
