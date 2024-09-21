package com.example.city_Taxi.controller;

import com.example.city_Taxi.dto.UserDTO;
import com.example.city_Taxi.service.UserService;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseMessage registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseMessage getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseMessage updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    public ResponseMessage getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/login")
    public ResponseMessage authenticateUser(@RequestBody UserDTO userDTO) {
        ResponseMessage authenticate = userService.authenticate(userDTO);
        return new ResponseMessage(200, Alert.ok, authenticate);
    }
}
