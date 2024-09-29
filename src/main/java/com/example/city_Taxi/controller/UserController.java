package com.example.city_Taxi.controller;

import com.example.city_Taxi.dto.UserDTO;
import com.example.city_Taxi.service.UserService;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        log.info("Register user by username {} user type {}", userDTO.getUsername(), userDTO.getUserType());
        ResponseMessage registerUser = userService.registerUser(userDTO);
        return new ResponseEntity<>(registerUser, HttpStatusCode.valueOf(registerUser.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        ResponseMessage response = userService.getUserById(id);
        log.info("User find by userID {}", id);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getCode()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        ResponseMessage updateUser = userService.updateUser(id, userDTO);
        log.info("User updated by userName {}", userDTO.getUsername());
        return new ResponseEntity<>(updateUser, HttpStatusCode.valueOf(updateUser.getCode()));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        ResponseMessage deleteUser = userService.deleteUser(id);
        log.info("User delete id {}", id);
        return new ResponseEntity<>(deleteUser, HttpStatusCode.valueOf(deleteUser.getCode()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        ResponseMessage getAllUsers = userService.getAllUsers();
        log.info("list of All Users {}", getAllUsers);
        return new ResponseEntity<>(getAllUsers, HttpStatusCode.valueOf(getAllUsers.getCode()));
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDTO userDTO) {
        ResponseMessage authenticate = userService.authenticate(userDTO);
        log.info("User named {} is Logged ", userDTO.getUsername());
        return new ResponseEntity<>(authenticate, HttpStatusCode.valueOf(authenticate.getCode()));
    }
}
