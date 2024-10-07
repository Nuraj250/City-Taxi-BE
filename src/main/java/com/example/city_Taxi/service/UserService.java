package com.example.city_Taxi.service;

import com.example.city_Taxi.dto.UserDTO;
import com.example.city_Taxi.model.User;
import com.example.city_Taxi.util.ResponseMessage;

public interface UserService {

    ResponseMessage registerUser(UserDTO userDTO);

    ResponseMessage registerUserByOperator(UserDTO userDTO);

    ResponseMessage updateUser(Long id, UserDTO userDTO);

    ResponseMessage getUserById(Long id);

    ResponseMessage deleteUser(Long id);

    ResponseMessage getAllUsers();

    User findUserByUsername(String username);

    ResponseMessage authenticate(UserDTO userDTO);
}
