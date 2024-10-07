package com.example.city_Taxi.service;

import com.example.city_Taxi.dto.UserDTO;
import com.example.city_Taxi.mapper.UserMapper;
import com.example.city_Taxi.model.User;
import com.example.city_Taxi.repository.UserRepository;
import com.example.city_Taxi.security.JwtTokenUtil;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final EmailService emailService;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, EmailService emailService, @Lazy UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.emailService = emailService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public ResponseMessage registerUser(final UserDTO userDTO) {
        try {
            User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            User save = userRepository.save(user);
            // Send the registration email
            emailService.sendRegistrationEmail(user.getEmail(), user.getUsername(), userDTO.getPassword());
            return new ResponseMessage(200, Alert.registerSuccess, user);
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage());
            return new ResponseMessage(500, Alert.registerFailed, null);
        }
    }

    @Override
    public ResponseMessage registerUserByOperator(UserDTO userDTO) {
        try {
            User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
            user.setPassword(null); // Do not save the password for operator registered users
            User savedUser = userRepository.save(user);
            return new ResponseMessage(200, "User registered successfully", savedUser);
        } catch (Exception e) {
            log.error("ERROR on registerUserByOperator {}", e.getMessage());

            return new ResponseMessage(500, "User registration failed", null);
        }
    }

    @Override
    public ResponseMessage updateUser(Long id, UserDTO userDTO) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setUserType(userDTO.getUserType());
            user.setProfileImage(userDTO.getProfileImage());
            userRepository.save(user);
            return new ResponseMessage(200, Alert.updateSuccess, user);
        }).orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new ResponseMessage(200, "User found", user)) // Status code 200 for success
                .orElseGet(() -> new ResponseMessage(404, "User not found", null)); // Status code 404 for not found
    }

    @Override
    public ResponseMessage deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return new ResponseMessage(200, Alert.removeSuccess, null);
        }).orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseMessage(200, Alert.ok, users);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public ResponseMessage authenticate(UserDTO userDTO) {
        User existUser = this.userRepository.findByUsername(userDTO.getUsername())
                .orElse(null);

        if (existUser == null) {
            return new ResponseMessage(404, Alert.nosuchfound, null); // User not found
        }
        // Check password match
        if (passwordEncoder.matches(userDTO.getPassword(), existUser.getPassword())) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());
            System.out.println(userDetails);
            final String token = jwtTokenUtil.generateToken(userDetails);
            Map<String, Object> userDetailsMap = Map.of(
                    "id", existUser.getId(),
                    "username", existUser.getUsername(),
                    "usertype", existUser.getUserType(),
                    "contact", existUser.getContact(),
                    "email", existUser.getEmail()
            );
            return new ResponseMessage(200, Alert.ok, userDetailsMap, token); // Success, return user
        } else {
            return new ResponseMessage(401, "Password doesn't match for user", null); // Unauthorized
        }
    }
}
