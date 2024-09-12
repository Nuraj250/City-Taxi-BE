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
            ResponseMessage responseMessage = userService.findUserByUsername(username);

            // If user is found (code == 200), return the user details in a new ResponseMessage
            if (responseMessage.getCode() == 200) {
                User registeredUser = (User) responseMessage.getObject();

                // Return a success ResponseMessage wrapping Spring Security's UserDetails object
                return (UserDetails) new ResponseMessage(200, Alert.ok,
                        new org.springframework.security.core.userdetails.User(
                                registeredUser.getUsername(),
                                registeredUser.getPassword(),
                                Collections.emptyList() // Add authorities if applicable
                        )
                );
            }

            // Return 404 if user is not found
            return (UserDetails) new ResponseMessage(404, Alert.nosuchfound, null);

        } catch (Exception ex) {
            // Log the error and return a generic error response
            log.error("Unexpected error occurred while loading user", ex);
            return (UserDetails) new ResponseMessage(500, Alert.backendError, null); // 500 for server error
        }

    }
}
