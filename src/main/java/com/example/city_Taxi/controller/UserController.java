package com.example.city_Taxi.controller;

import com.example.city_Taxi.dto.UserDTO;
import com.example.city_Taxi.service.UserService;
import com.example.city_Taxi.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
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
}
